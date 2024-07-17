import {createTransport, Transporter} from "nodemailer";

export class EmailService{
    private _to:string;
    private _subject: string;
    private _transporter: Transporter;
    constructor(to: string, subject: string) {
        this._to = to;
        this._subject = subject;
        this._transporter = this.createTransporter();
    }
    private createTransporter(){
        return createTransport({
            host: process.env.MAIL_HOST,
            port: parseInt(process.env.MAIL_PORT || ''),
            secure: false,
            auth:{
                user: process.env.MAIL_USER,
                pass: process.env.MAIL_PASS,
            }
        })
    }
   sendHtmlBody(body:string){
        this._transporter.sendMail({
            from: process.env.MAIL_FROM,
            to: this._to,
            subject: this._subject,
            html: body
        });
    }
    sendTextBody(to:string,subject:string,body:string){
        this._transporter.sendMail({
            from: process.env.MAIL_FROM,
            to,
            subject,
            text: body
        });
    }
}





