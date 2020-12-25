package easymall.common;

import java.util.Date;

/**
 * @author passerbyYSQ
 * @create 2020-11-01 21:04
 */
public class ResultModel<T>  {

    // ҵ��״̬��
    private Integer code;
    // ״̬������Ϣ
    private String msg;
    // ���ص�����
    private T data;
    // ��Ӧʱ��
    private Date time = new Date();

    // ȫ�ι���
    public ResultModel(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    // û��data�Ĺ���
    public ResultModel(Integer code, String msg) {
        this(code, msg, null);
    }

    // �ɹ������н����
    public static <T> ResultModel<T> success(T data) {
        return new ResultModel<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), data);
    }
    // �ɹ���û�н����
    public static <T> ResultModel<T> success() {
        return success(null);
    }

    // �������
    public static <T> ResultModel<T> error(Integer code, String msg) {
        return new ResultModel<>(code, msg);
    }

    // ���������ö�����м��ж��壬Ȼ����ָ��ö�������
    public static <T> ResultModel<T> error(StatusCode code) {
        return error(code.getCode(), code.getMsg());
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}

}
