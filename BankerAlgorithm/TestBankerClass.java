package BankerAlgorithm;

import java.util.Scanner;

public class TestBankerClass {


    public static void main(String[] args) {
    	BankerClass bankerClass = new BankerClass();
    	Scanner scanner = new Scanner(System.in);
    	String choice;
    	System.out.print("\t---------------------------------------------------\n");
    	System.out.print("\t||                                               ||\n");
    	System.out.print("\t||               银行家算法的实现                ||\n");
    	System.out.print("\t||                                               ||\n");
    	System.out.print("\t||                                               ||\n");
    	System.out.print("\t||                     在此输入个人姓名：小贺    ||\n");
    	System.out.print("\t||                                               ||\n");
    	System.out.print("\t---------------------------------------------------\n");
    	bankerClass.init();//初始化数据
        bankerClass.showdata();//显示各种资源
        bankerClass.safe();//用银行家算法判定系统当前是否安全 (是否可以全部运行完)
        System.out.print("*************************************************************\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\t-------------------银行家算法演示------------------\n");
        System.out.print("                     R(r):请求分配   \n");
        System.out.print("                     E(e):退出       \n");
        System.out.print("\t---------------------------------------------------\n");
        System.out.print("请选择：");
    		choice=scanner.next();
    	while(choice != null)
    	{
    		switch(choice)
    		{
    			case "r":
    			case "R":
    				bankerClass.bank();break;
    			case "e":
    			case "E":
    				System.out.println("退出!");break;
    			default: System.out.print("请正确选择!\n");break;
    		}
    	}
    
    
    
    }
    
}
