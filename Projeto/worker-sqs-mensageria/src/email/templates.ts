export const emailMessageTemplate = (url: string) => {
    return "<!DOCTYPE html>" +
        "<html lang=\"en\">" +
        "<head>" +
        "    <meta charset=\"UTF-8\">" +
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
        "    <title>Confirmação de Email</title>" +
        "</head>" +
        "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;\">" +
        "" +
        "<table style=\"max-width: 600px; width: 100%; margin: 0 auto; background-color: #fff; border-radius: 8px; overflow: hidden;\">" +
        "    <tr>" +
        "        <td style=\"padding: 20px;\">" +
        "            <h2 style=\"color: #333;\">Confirmação de Email</h2>" +
        "            <p style=\"color: #666;\">Olá,</p>" +
        "            <p style=\"color: #666;\">Para confirmar seu endereço de email, por favor clique no link abaixo:</p>" +
        "            <p style=\"text-align: center;\">" +
        "                <a href=\""+url+"\" style=\"display: inline-block; padding: 10px 20px; background-color: #007bff; color: #fff; text-decoration: none; border-radius: 5px;\">Confirmar Email</a>" +
        "            </p>" +
        "            <p style=\"color: #666;\">Se você não solicitou esta confirmação, ignore este email.</p>" +
        "            <p style=\"color: #666;\">Obrigado,</p>" +
        "            <p style=\"color: #666;\">Equipe do Sarapatel</p>" +
        "        </td>" +
        "    </tr>" +
        "</table>" +
        "" +
        "</body>" +
        "</html>";
}