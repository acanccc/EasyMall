package easymall.common;

/**
 * 2000 - �ɹ���������
 * 3*** - �ض�����Ҫ��һ���Ĳ������������
 * 4*** - �ͻ��˴���������������﷨����ȵ�
 * 5*** - �������ڲ�����
 * ...
 *
 * @author passerbyYSQ
 * @create 2020-11-02 16:26
 */
// �����ϴ�ע�⣬Jackson���������л�Ϊjsonʱ��ֱ�ӽ�ö����ת����������
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusCode {
    SUCCESS(2000, "�ɹ�"),

    // �������ڲ�����
    UNKNOWN_ERROR(5000, "δ֪����"),

    // �������
    PARAM_NOT_COMPLETED(6001, "����ȱʧ"),
    PARAM_IS_INVALID(6002, "������Ч"),
    FILE_TYPE_INVALID(6003, "�Ƿ��ļ�����"),
    FILE_SIZE_EXCEEDED(6004, "�ļ���С��������"),
    CAPTCHA_INCORRECT(6005, "��֤�����"),
    PASSWORD_INCORRECT(6006, "�������"),

    // �û����
    USER_IS_EXIST(6101, "�û��Ѵ���"),
    USERNAME_IS_EXIST(6102, "�û����ѱ�ʹ��"),
    USER_NOT_EXIST(6103, "�û������ڣ��������"),
    EMAIL_NOT_EXIST(6104, "������δע��"),
    EMAIL_SEND_FREQUENT(6105, "Ƶ�����ͣ������ϴ�������֤�뷢�Ͳ���60��"),
    USER_NOT_LOGIN(6106, "�û�δ��¼"),

    // �˻����
    TOKEN_IS_MISSING(6200, "tokenȱʧ"),
    FORCED_OFFLINE(6201, "��ص�¼����ǰ�˻���������"),
    TOKEN_IS_EXPIRED(6202, "token�ѹ���"),
    TOKEN_IS_INVALID(6203, "��Чtoken"),
    LOGIN_FAILED(6204, "��¼ʧ�ܣ��û������������"),
    REGISTER_FAILED(6205, "ע��ʧ�ܣ�δ֪����"),
    
    // ���ﳵ���
    // ����pid���󣬻�����Ȩ�޸����˹��ﳵ�е���Ʒ����
    CART_NUM_UPDATE_FAILED(6300, "���ﳵ��Ʒ�Ĺ�����������ʧ��"),
    CART_ONE_ITEM_DELETE_FAILED(6301, "���ﳵ��Ʒɾ��ʧ��"),
    CART_MORE_ITEM_DELETE_FAILED(6302, "���ֹ��ﳵ��Ʒɾ��ʧ��"),
    
    // �������
    ORDER_DELETE_FAILED(6400, "����ɾ��ʧ�ܣ������Ŵ���")
    ;

    // ״̬����ֵ
    private Integer code;
    // ״̬��������Ϣ
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
