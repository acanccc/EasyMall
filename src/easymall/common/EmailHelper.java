package easymall.common;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * API�ĵ���https://help.aliyun.com/document_detail/29459.html?spm=a2c4g.11186623.6.635.4fbe5386F2ObvZ
 * 
 * @author passerbyYSQ
 * @create 2020-03-17 12:48
 */
public class EmailHelper {

    private static final String regionId = "cn-hangzhou";
    private static final String accessKeyId = "LTAI4FnM6LpGsjE2qJ8sEqkA";
    private static final String secret = "LjVhzlltTdYXbeOfrFUtapmhzWhYut";

    public static IAcsClient getClient() {
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        return new DefaultAcsClient(profile);
    }


    public static SingleSendMailResponse singleSend(EmailBean email) {
        try {
            IAcsClient client = getClient();
            SingleSendMailRequest request = email.buildRequest();
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
            return httpResponse;
        } catch (ServerException e) {
            //��������쳣��
            System.out.println("ErrCode :"  + e.getErrCode());
            e.printStackTrace();
        }
        catch (ClientException e) {
            //��������쳣��
            System.out.println("ErrCode : " + e.getErrCode());
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        singleSend(new EmailBean("idea", "1127664027@qq.com", "����",
//                "<h1>���ԡ�������</h1>"));
//    }

    public static class EmailBean {
        // ���ŵ�ַ��һ�㲻ȥ����
        private String accountName = "passerbyysq@email.ysqorz.top";
        // �����˵�����
        private String alias;
        // ����������
        private String toAddress;
        // �ʼ�����
        private String subject;
        // �ʼ�����
        private String htmlBody;

        // ����ʽ
        private MethodType method = MethodType.POST;
        //
        private boolean replyToAddress = false;
        // Tag
        private String tagName;

        // ������Ϣ�Ĺ��캯��
        /**
         * @param alias		�����˵�����
         * @param toAddress	����������	
         * @param subject	�ʼ�����
         * @param htmlBody	�ʼ����ݡ�֧��Html
         */
        public EmailBean(String alias, String toAddress, String subject, String htmlBody) {
            this.alias = alias;
            this.toAddress = toAddress;
            this.subject = subject;
            this.htmlBody = htmlBody;
        }

        public SingleSendMailRequest buildRequest() {
            SingleSendMailRequest request = new SingleSendMailRequest();
            request.setAccountName(accountName);
            request.setFromAlias(alias);
            request.setAddressType(1);
            request.setTagName(tagName);
            request.setReplyToAddress(replyToAddress);
            request.setToAddress(toAddress);
            request.setSubject(subject);
            //�������byte[].toString�ķ�ʽ�Ļ���ȷ������ת����utf-8�ĸ�ʽ�ٷ���htmlbody��textbody��
            // �����벻һ����ᱻ���������ʼ���
            //ע�⣺�ı��ʼ��Ĵ�С����Ϊ3M��������ı��ᵼ�����ӳ�ʱ��413����
            request.setHtmlBody(htmlBody);
            request.setMethod(method);
            //������Ҫ������0�رգ�1����
            //request.setClickTrace(��0��);
            return request;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getToAddress() {
            return toAddress;
        }

        public void setToAddress(String toAddress) {
            this.toAddress = toAddress;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getHtmlBody() {
            return htmlBody;
        }

        public void setHtmlBody(String htmlBody) {
            this.htmlBody = htmlBody;
        }

        public MethodType getMethod() {
            return method;
        }

        public void setMethod(MethodType method) {
            this.method = method;
        }

        public boolean isReplyToAddress() {
            return replyToAddress;
        }

        public void setReplyToAddress(boolean replyToAddress) {
            this.replyToAddress = replyToAddress;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }
    }



}

