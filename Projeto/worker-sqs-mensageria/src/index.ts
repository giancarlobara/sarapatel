import { SqsConsumerFactory} from "./queue/sqs-consumer-factory";
import config from './queue/config';
import {EmailService} from "./email/email-service";
import {emailMessageTemplate} from "./email/templates";



const isJson = (str: string) => {
    try {
        JSON.parse(str);
        return true;
    } catch (e) {
        return false;
    }
};

const messageHandler = async (message: any) => {
    const body = isJson(message.Body) ? JSON.parse(message.Body) : message.Body;
    const email = Buffer.from(body.Email,'base64').toString('ascii');
    const url = Buffer.from(body.Url,'base64').toString('ascii');

    const emailService = new EmailService(email,"Confirmação de email");
    emailService.sendHtmlBody(emailMessageTemplate(url));
};

const startConsume = async () => {
    try {
        console.log('queue consumer start');
        const { queueUrl, region } = config.sqs;

        const pilotQueue = SqsConsumerFactory.create(
            {
                queueUrl,
                region,
            },
            messageHandler,
        );
        pilotQueue
            .handleError((err) => {
                console.error(err);
            })
            .start();

    } catch (error) {
        console.error(error);
    }
};

startConsume();