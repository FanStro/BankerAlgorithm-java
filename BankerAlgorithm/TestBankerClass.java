package BankerAlgorithm;

import java.util.Scanner;

public class TestBankerClass {


    public static void main(String[] args) {
    	BankerClass bankerClass = new BankerClass();
    	Scanner scanner = new Scanner(System.in);
    	String choice;
    	System.out.print("\t---------------------------------------------------\n");
    	System.out.print("\t||                                               ||\n");
    	System.out.print("\t||               ���м��㷨��ʵ��                ||\n");
    	System.out.print("\t||                                               ||\n");
    	System.out.print("\t||                                               ||\n");
    	System.out.print("\t||                     �ڴ��������������С��    ||\n");
    	System.out.print("\t||                                               ||\n");
    	System.out.print("\t---------------------------------------------------\n");
    	bankerClass.init();//��ʼ������
        bankerClass.showdata();//��ʾ������Դ
        bankerClass.safe();//�����м��㷨�ж�ϵͳ��ǰ�Ƿ�ȫ (�Ƿ����ȫ��������)
        System.out.print("*************************************************************\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\t-------------------���м��㷨��ʾ------------------\n");
        System.out.print("                     R(r):�������   \n");
        System.out.print("                     E(e):�˳�       \n");
        System.out.print("\t---------------------------------------------------\n");
        System.out.print("��ѡ��");
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
    				System.out.println("�˳�!");break;
    			default: System.out.print("����ȷѡ��!\n");break;
    		}
    	}
    
    
    
    }
    
}
