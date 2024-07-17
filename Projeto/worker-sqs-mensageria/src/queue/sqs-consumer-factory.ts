import {SQSClient} from "@aws-sdk/client-sqs";
import {Consumer, ConsumerOptions} from "sqs-consumer";

export type SQSErrorHandler = (err: any) => any;

export class SqsConsumerFactory {
    private _queueConsumer: Consumer;
    private _config: ConsumerOptions;
    private constructor(config: ConsumerOptions) {
        this._config = config;
        this._queueConsumer = Consumer.create(this._config);
    }
    get queueConsumer(){
        return this._queueConsumer;
    }
    static create(
        config: ConsumerOptions,
        messageHandler: (message: unknown) => Promise<void>
    ){
        const sqsConfig: ConsumerOptions = {
            queueUrl:config.queueUrl,
            sqs: new SQSClient({
                region: config.region,
                credentials:{
                    accessKeyId: process.env.QUEUE_ACCESS_KEY || "",
                    secretAccessKey: process.env.QUEUE_SECRET_KEY || "",
                }

            }),
            handleMessage: messageHandler
        };
        return new SqsConsumerFactory(sqsConfig)
    };
    handleError(errHandlerFn: SQSErrorHandler): SqsConsumerFactory{
        this.queueConsumer.on('error',errHandlerFn);
        return this;
    }
    start(): SqsConsumerFactory{
        this.queueConsumer.start();
        return this;
    }
}