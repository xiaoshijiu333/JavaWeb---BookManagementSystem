package Tools;

//用来判断是否是邮箱登录

public class isEmail {
	
	public static boolean  isemail(String name) {
		
		boolean flag1 = false;
		boolean flag2 = false;
		
		for(int i=0;i<name.length();i++) {
			if(name.charAt(i)=='@') {
				flag1=true;
			}
			if(name.charAt(i)=='.') {
				flag2=true;
			}
		}
		
		if(flag1&&flag2) {
			return true;
		}
		
		return false;
		
	}
}
