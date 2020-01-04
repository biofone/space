package cn.chaochao.exception;

public class MsgException extends RuntimeException{
    public MsgException(){

    }
    public MsgException(String str){
        super(str);
    }
}
