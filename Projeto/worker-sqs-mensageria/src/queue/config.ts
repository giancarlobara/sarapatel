import * as dotenv from 'dotenv';
dotenv.config();
export default {
    sqs:{
        queueUrl: process.env.QUEUE_URL || '',
        region: process.env.QUEUE_REGION || '',
    },

};