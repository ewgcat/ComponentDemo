package com.yijian.staff.mvp.workspace.webutils;

import android.os.Handler;
import android.webkit.JavascriptInterface;

import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;

public final class JavaScriptInterface {

	private CallBackListener callBackListener;
	private Handler mHandler = new Handler();

	public JavaScriptInterface(CallBackListener callBackListener) {
		this.callBackListener = callBackListener;
	}

	/**测试结果**/
	public static final int JS_GetWorkspaceToaken = 1;
	/**获取结果ID**/
	public static final int JS_returnTestWdId = 2;
	/**触发测试按钮事件**/
	public static final int JS_GoWorkspaceTest = 3;
	/**触发其它测试按钮事件**/
	public static final int JS_GoWorkspaceOtherTest = 4;
	/**获取MemberId**/
	public static final int JS_returnTestMemberId = 5;

	@JavascriptInterface
	public String getWorkSpaceToken(){
		return (String)callBackListener.callBack("",JS_GetWorkspaceToaken);
//		return "token232424234";
	}

	@JavascriptInterface
	public String getWorkSpaceId(){
		return (String)callBackListener.callBack("",JS_returnTestWdId);
	}

	@JavascriptInterface
	public String getWorkSpaceMemberId(){
		return (String)callBackListener.callBack("",JS_returnTestMemberId);
	}

	@JavascriptInterface
	public void goWorkSpaceTest(){
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				if(null != callBackListener){
					callBackListener.callBack("",JS_GoWorkspaceTest);
				}
			}
		});
	}

	@JavascriptInterface
	public void goWorkSpaceOtherTest(){
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				if(null != callBackListener){
					callBackListener.callBack("",JS_GoWorkspaceOtherTest);
				}
			}
		});
	}

	public CallBackListener getCallBackListener() {
		return callBackListener;
	}

	public void setCallBackListener(CallBackListener callBackListener) {
		this.callBackListener = callBackListener;
	}

	public interface CallBackListener{
		Object callBack(String msg,int type);
	}

	
}
