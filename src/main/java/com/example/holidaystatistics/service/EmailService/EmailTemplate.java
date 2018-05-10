package com.example.holidaystatistics.service.EmailService;

import org.springframework.stereotype.Service;

/**
 * @author nitmali@126.com
 * @date 2018/5/9 13:48
 */

@Service
class EmailTemplate {
    String getEmailTemplate(String address) {
        //HTML
        String emailTemplate = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <base target=\"_blank\">\n" +
            "    <style type=\"text/css\">\n" +
            "        ::-webkit-scrollbar {\n" +
            "            display: none;\n" +
            "        }\n" +
            "    </style>\n" +
            "    <style id=\"cloudAttachStyle\" type=\"text/css\">\n" +
            "        #divNeteaseBigAttach, #divNeteaseBigAttach_bak {\n" +
            "            display: none;\n" +
            "        }\n" +
            "    </style>\n" +
            "\n" +
            "</head>\n" +
            "<body tabindex=\"0\" role=\"listitem\">\n" +
            "\n" +
            "\n" +
            "<style type=\"text/css\">\n" +
            "    img {\n" +
            "        max-width: 100%;\n" +
            "    }\n" +
            "\n" +
            "    body {\n" +
            "        -webkit-font-smoothing: antialiased;\n" +
            "        -webkit-text-size-adjust: none;\n" +
            "        width: 100% !important;\n" +
            "        height: 100%;\n" +
            "        line-height: 1.6em;\n" +
            "    }\n" +
            "\n" +
            "    body {\n" +
            "        background-color: #f6f6f6;\n" +
            "    }\n" +
            "\n" +
            "    _media only screen\n" +
            "\n" +
            "    (\n" +
            "    max-width:\n" +
            "\n" +
            "    640\n" +
            "    px\n" +
            "\n" +
            "    )\n" +
            "    {\n" +
            "    body {\n" +
            "        padding: 0 !important;\n" +
            "    }\n" +
            "\n" +
            "    h1 {\n" +
            "        font-weight: 800 !important;\n" +
            "        margin: 20px 0 5px !important;\n" +
            "    }\n" +
            "\n" +
            "    h2 {\n" +
            "        font-weight: 800 !important;\n" +
            "        margin: 20px 0 5px !important;\n" +
            "    }\n" +
            "\n" +
            "    h3 {\n" +
            "        font-weight: 800 !important;\n" +
            "        margin: 20px 0 5px !important;\n" +
            "    }\n" +
            "\n" +
            "    h4 {\n" +
            "        font-weight: 800 !important;\n" +
            "        margin: 20px 0 5px !important;\n" +
            "    }\n" +
            "\n" +
            "    h1 {\n" +
            "        font-size: 22px !important;\n" +
            "    }\n" +
            "\n" +
            "    h2 {\n" +
            "        font-size: 18px !important;\n" +
            "    }\n" +
            "\n" +
            "    h3 {\n" +
            "        font-size: 16px !important;\n" +
            "    }\n" +
            "\n" +
            "    .container {\n" +
            "        padding: 0 !important;\n" +
            "        width: 100% !important;\n" +
            "    }\n" +
            "\n" +
            "    .content {\n" +
            "        padding: 0 !important;\n" +
            "    }\n" +
            "\n" +
            "    .content-wrap {\n" +
            "        padding: 10px !important;\n" +
            "    }\n" +
            "\n" +
            "    .invoice {\n" +
            "        width: 100% !important;\n" +
            "    }\n" +
            "\n" +
            "    }\n" +
            "</style>\n" +
            "\n" +
            "\n" +
            "<table class=\"body-wrap\"\n" +
            "       style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; background-color: #f6f6f6; margin: 0;\"\n" +
            "       bgcolor=\"#f6f6f6\">\n" +
            "    <tbody>\n" +
            "    <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
            "        <td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\"\n" +
            "            valign=\"top\"></td>\n" +
            "        <td class=\"container\" width=\"600\"\n" +
            "            style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; display: block !important; max-width: 600px !important; clear: both !important; margin: 0 auto;\"\n" +
            "            valign=\"top\">\n" +
            "            <div class=\"content\"\n" +
            "                 style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; max-width: 600px; display: block; margin: 0 auto; padding: 20px;\">\n" +
            "                <table class=\"main\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" itemprop=\"action\" itemscope=\"\"\n" +
            "                       itemtype=\"http://schema.org/ConfirmAction\"\n" +
            "                       style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; border-radius: 3px; background-color: #fff; margin: 0; border: 1px solid #e9e9e9;\"\n" +
            "                       bgcolor=\"#fff\">\n" +
            "                    <tbody>\n" +
            "                    <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; \n" +
            "                    box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
            "                        <td class=\"content-wrap\"\n" +
            "                            style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; \n" +
            "                            box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 20px;\"\n" +
            "                            valign=\"top\">\n" +
            "                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"\n" +
            "                                   style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; \n" +
            "                                   box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
            "                                <tbody>\n" +
            "                                <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; \n" +
            "                                box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
            "                                    <td class=\"content-block\"\n" +
            "                                        style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; \n" +
            "                                        box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\"\n" +
            "                                        valign=\"top\">\n" +
            "                                        您收到此邮件是因为您在holiday.nitmali.com申请了密码重置,如果不是您申请的,请忽略此邮件.\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                                <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; \n" +
            "                                box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
            "                                    <td class=\"content-block\"\n" +
            "                                        style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; \n" +
            "                                        box-sizing: border-box; font-size: 14px; vertical-align: top; \n" +
            "                                        margin: 0; padding: 0 0 20px;\"\n" +
            "                                        valign=\"top\">\n" +
            "                                        若要开始重置密码,请点击以下链接 (此链接将在10分钟后或重置密码后失效.)\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                                <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; \n" +
            "                                box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
            "                                    <td class=\"content-block\" itemprop=\"handler\" itemscope=\"\"\n" +
            "                                        itemtype=\"http://schema.org/HttpActionHandler\"\n" +
            "                                        style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; \n" +
            "                                        box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\"\n" +
            "                                        valign=\"top\">\n" +
            "                                        <a href=\""+address+"\"\n" +
            "                                           class=\"btn-primary\" itemprop=\"url\"\n" +
            "                                           style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; \n" +
            "                                           box-sizing: border-box; font-size: 14px; color: #FFF; text-decoration: none;\n" +
            "                                            line-height: 2em; font-weight: bold; text-align: center; cursor: pointer; \n" +
            "                                            display: inline-block; border-radius: 5px; text-transform: capitalize; \n" +
            "                                            background-color: #348eda; margin: 0; border-color: #348eda; \n" +
            "                                            border-style: solid; border-width: 10px 20px;\">点击此链接重置密码</a>\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                                <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
            "                                    <td class=\"content-block\"\n" +
            "                                        style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\"\n" +
            "                                        valign=\"top\">\n" +
            "                                        — nitmali\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                                </tbody>\n" +
            "                            </table>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                    </tbody>\n" +
            "                </table>\n" +
            "                <div class=\"footer\"\n" +
            "                     style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; clear: both; color: #999; margin: 0; padding: 20px;\">\n" +
            "                    <table width=\"100%\"\n" +
            "                           style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
            "                        <tbody>\n" +
            "                        <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
            "                            <td class=\"aligncenter content-block\"\n" +
            "                                style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 12px; vertical-align: top; color: #999; text-align: center; margin: 0; padding: 0 0 20px;\"\n" +
            "                                align=\"center\" valign=\"top\">\n" +
            "                                <a href=\"http://holiday.nitmali.com\"\n" +
            "                                   style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 12px; color: #999; text-decoration: underline; margin: 0;\">\n" +
            "                                    holiday.nitmali.com\n" +
            "                                </a> .\n" +
            "                            </td>\n" +
            "                        </tr>\n" +
            "                        </tbody>\n" +
            "                    </table>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </td>\n" +
            "        <td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\"\n" +
            "            valign=\"top\"></td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>\n" +
            "<div style=\"background:#eee; border:1px solid #ccc; padding:5px 10px\"><strong>请不要回复此电子邮件.&nbsp;\n" +
            "    如果你需要一些帮助或给我们建议，请联系&nbsp;<a href=\"mailto:me@nitmali.com\">me@nitmali.com</a>&nbsp;.</strong>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "<style type=\"text/css\">\n" +
            "    body {\n" +
            "        font-size: 14px;\n" +
            "        font-family: arial, verdana, sans-serif;\n" +
            "        line-height: 1.666;\n" +
            "        padding: 0;\n" +
            "        margin: 0;\n" +
            "        overflow: auto;\n" +
            "        white-space: normal;\n" +
            "        word-wrap: break-word;\n" +
            "        min-height: 100px\n" +
            "    }\n" +
            "\n" +
            "    td, input, button, select, body {\n" +
            "        font-family: Helvetica, 'Microsoft Yahei', verdana\n" +
            "    }\n" +
            "\n" +
            "    pre {\n" +
            "        white-space: pre-wrap;\n" +
            "        white-space: -moz-pre-wrap;\n" +
            "        white-space: -pre-wrap;\n" +
            "        white-space: -o-pre-wrap;\n" +
            "        word-wrap: break-word;\n" +
            "        width: 95%\n" +
            "    }\n" +
            "\n" +
            "    th, td {\n" +
            "        font-family: arial, verdana, sans-serif;\n" +
            "        line-height: 1.666\n" +
            "    }\n" +
            "\n" +
            "    img {\n" +
            "        border: 0\n" +
            "    }\n" +
            "\n" +
            "    header, footer, section, aside, article, nav, hgroup, figure, figcaption {\n" +
            "        display: block\n" +
            "    }\n" +
            "\n" +
            "    blockquote {\n" +
            "        margin-right: 0px\n" +
            "    }\n" +
            "</style>\n" +
            "\n" +
            "<style id=\"ntes_link_color\" type=\"text/css\">a, td a {\n" +
            "    color: #138144\n" +
            "}</style>\n" +
            "\n" +
            "</body>\n" +
            "</html>";

        return emailTemplate;
    }

    String getBindEmailTemplate(String token){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <base target=\"_blank\">\n" +
                "    <style type=\"text/css\">\n" +
                "        ::-webkit-scrollbar {\n" +
                "            display: none;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <style id=\"cloudAttachStyle\" type=\"text/css\">\n" +
                "        #divNeteaseBigAttach, #divNeteaseBigAttach_bak {\n" +
                "            display: none;\n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "</head>\n" +
                "<body tabindex=\"0\" role=\"listitem\">\n" +
                "\n" +
                "\n" +
                "<style type=\"text/css\">\n" +
                "    img {\n" +
                "        max-width: 100%;\n" +
                "    }\n" +
                "\n" +
                "    body {\n" +
                "        -webkit-font-smoothing: antialiased;\n" +
                "        -webkit-text-size-adjust: none;\n" +
                "        width: 100% !important;\n" +
                "        height: 100%;\n" +
                "        line-height: 1.6em;\n" +
                "    }\n" +
                "\n" +
                "    body {\n" +
                "        background-color: #f6f6f6;\n" +
                "    }\n" +
                "\n" +
                "    _media only screen\n" +
                "\n" +
                "    (\n" +
                "    max-width:\n" +
                "\n" +
                "    640\n" +
                "    px\n" +
                "\n" +
                "    )\n" +
                "    {\n" +
                "    body {\n" +
                "        padding: 0 !important;\n" +
                "    }\n" +
                "\n" +
                "    h1 {\n" +
                "        font-weight: 800 !important;\n" +
                "        margin: 20px 0 5px !important;\n" +
                "    }\n" +
                "\n" +
                "    h2 {\n" +
                "        font-weight: 800 !important;\n" +
                "        margin: 20px 0 5px !important;\n" +
                "    }\n" +
                "\n" +
                "    h3 {\n" +
                "        font-weight: 800 !important;\n" +
                "        margin: 20px 0 5px !important;\n" +
                "    }\n" +
                "\n" +
                "    h4 {\n" +
                "        font-weight: 800 !important;\n" +
                "        margin: 20px 0 5px !important;\n" +
                "    }\n" +
                "\n" +
                "    h1 {\n" +
                "        font-size: 22px !important;\n" +
                "    }\n" +
                "\n" +
                "    h2 {\n" +
                "        font-size: 18px !important;\n" +
                "    }\n" +
                "\n" +
                "    h3 {\n" +
                "        font-size: 16px !important;\n" +
                "    }\n" +
                "\n" +
                "    .container {\n" +
                "        padding: 0 !important;\n" +
                "        width: 100% !important;\n" +
                "    }\n" +
                "\n" +
                "    .content {\n" +
                "        padding: 0 !important;\n" +
                "    }\n" +
                "\n" +
                "    .content-wrap {\n" +
                "        padding: 10px !important;\n" +
                "    }\n" +
                "\n" +
                "    .invoice {\n" +
                "        width: 100% !important;\n" +
                "    }\n" +
                "\n" +
                "    }\n" +
                "</style>\n" +
                "\n" +
                "\n" +
                "<table class=\"body-wrap\"\n" +
                "       style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; background-color: #f6f6f6; margin: 0;\"\n" +
                "       bgcolor=\"#f6f6f6\">\n" +
                "    <tbody>\n" +
                "    <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
                "        <td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\"\n" +
                "            valign=\"top\"></td>\n" +
                "        <td class=\"container\" width=\"600\"\n" +
                "            style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; display: block !important; max-width: 600px !important; clear: both !important; margin: 0 auto;\"\n" +
                "            valign=\"top\">\n" +
                "            <div class=\"content\"\n" +
                "                 style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; max-width: 600px; display: block; margin: 0 auto; padding: 20px;\">\n" +
                "                <table class=\"main\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" itemprop=\"action\" itemscope=\"\"\n" +
                "                       itemtype=\"http://schema.org/ConfirmAction\"\n" +
                "                       style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; border-radius: 3px; background-color: #fff; margin: 0; border: 1px solid #e9e9e9;\"\n" +
                "                       bgcolor=\"#fff\">\n" +
                "                    <tbody>\n" +
                "                    <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
                "                        <td class=\"content-wrap\"\n" +
                "                            style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 20px;\"\n" +
                "                            valign=\"top\">\n" +
                "                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                   style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
                "                                <tbody>\n" +
                "                                <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
                "                                    <td class=\"content-block\"\n" +
                "                                        style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\"\n" +
                "                                        valign=\"top\">\n" +
                "                                        您收到此邮件是因为您在holiday.nitmali.com申请了绑定邮箱,如果不是您申请的,请忽略此邮件.\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
                "                                    <td class=\"content-block\"\n" +
                "                                        style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\"\n" +
                "                                        valign=\"top\">\n" +
                "                                        下面是您本次的验证码(此验证码将在10分钟后或重置密码后失效.)\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
                "                                    <td>\n" +
                "                                        <h3 style=\"color: blue\"> "+token+"</h3>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
                "                                    <td class=\"content-block\"\n" +
                "                                        style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\"\n" +
                "                                        valign=\"top\">\n" +
                "                                        — nitmali\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "                <div class=\"footer\"\n" +
                "                     style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; clear: both; color: #999; margin: 0; padding: 20px;\">\n" +
                "                    <table width=\"100%\"\n" +
                "                           style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
                "                        <tbody>\n" +
                "                        <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
                "                            <td class=\"aligncenter content-block\"\n" +
                "                                style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 12px; vertical-align: top; color: #999; text-align: center; margin: 0; padding: 0 0 20px;\"\n" +
                "                                align=\"center\" valign=\"top\">\n" +
                "                                <a href=\"http://holiday.nitmali.com\"\n" +
                "                                    style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 12px; color: #999; text-decoration: underline; margin: 0;\">\n" +
                "                                    holiday.nitmali.com\n" +
                "                                </a> .\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </td>\n" +
                "        <td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\"\n" +
                "            valign=\"top\"></td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "<div style=\"background:#eee; border:1px solid #ccc; padding:5px 10px\"><strong>请不要回复此电子邮件.&nbsp;If\n" +
                "    如果你需要一些帮助或给我们建议，请联系&nbsp;<a href=\"mailto:me@nitmali.com\">me@nitmali.com</a>&nbsp;.</strong>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<style type=\"text/css\">\n" +
                "    body {\n" +
                "        font-size: 14px;\n" +
                "        font-family: arial, verdana, sans-serif;\n" +
                "        line-height: 1.666;\n" +
                "        padding: 0;\n" +
                "        margin: 0;\n" +
                "        overflow: auto;\n" +
                "        white-space: normal;\n" +
                "        word-wrap: break-word;\n" +
                "        min-height: 100px\n" +
                "    }\n" +
                "\n" +
                "    td, input, button, select, body {\n" +
                "        font-family: Helvetica, 'Microsoft Yahei', verdana\n" +
                "    }\n" +
                "\n" +
                "    pre {\n" +
                "        white-space: pre-wrap;\n" +
                "        white-space: -moz-pre-wrap;\n" +
                "        white-space: -pre-wrap;\n" +
                "        white-space: -o-pre-wrap;\n" +
                "        word-wrap: break-word;\n" +
                "        width: 95%\n" +
                "    }\n" +
                "\n" +
                "    th, td {\n" +
                "        font-family: arial, verdana, sans-serif;\n" +
                "        line-height: 1.666\n" +
                "    }\n" +
                "\n" +
                "    img {\n" +
                "        border: 0\n" +
                "    }\n" +
                "\n" +
                "    header, footer, section, aside, article, nav, hgroup, figure, figcaption {\n" +
                "        display: block\n" +
                "    }\n" +
                "\n" +
                "    blockquote {\n" +
                "        margin-right: 0px\n" +
                "    }\n" +
                "</style>\n" +
                "\n" +
                "<style id=\"ntes_link_color\" type=\"text/css\">a, td a {\n" +
                "    color: #138144\n" +
                "}</style>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }

}
