package BankerAlgorithm;

import java.util.Scanner;

public class BankerClass {
	
	String[] Name = new String[100]; // ��Դ������
	int[][] Max = new int[100][100];
	int[] Available = new int[100];// ������Դ����
	int[][] Allocation = new int[100][100];// ϵͳ�ѷ������
	int[][] Need = new int[100][100];// ����Ҫ��Դ����
	int[] Request = new int[100];// ������Դ����
	int[] Security = new int[100];// ��Ű�ȫ����
	int[] Work = new int[100];// ���ϵͳ���ṩ��Դ
	int M = 100;// ���̵������
	int N = 100;// ��Դ�������

	Scanner in = new Scanner(System.in);

	/********
	 * ��ʼ�����ݣ����������������Դ���ࡢ������Դ������������ �����̵���Դ�ѷ��������������̶���Դ����������ȡ�
	 ********/
	public void init() {
		/* mΪ���̸�����������������nΪ��Դ���࣬������������ */
		int i, j, m, n;
		int number;
		boolean flag;
		String name;// ������Դ����
		int[] temp = new int[100];// ͳ���Ѿ��������Դ
		System.out.println("ϵͳ������Դ����Ϊ:");
		n = in.nextInt();
		N = n;
		for (i = 0; i < n; i++)// ������Դ��Ϣ����Դ���Ƽ�����
		{
			System.out.println("��Դ" + i + "������:");
			name = in.next(); // ������%c,�������
			Name[i] = name;
			System.out.println("��Դ" + name + "�ĳ�ʼ����Ϊ:");
			number = in.nextInt();
			Available[i] = number;
		}
		System.out.print("\n");
		System.out.println("��������̵�����:");
		m = in.nextInt();
		M = m;
		System.out.println("����������̵������������ֵ[Max]:");
		for (i = 0; i < m; i++)// ����Max����
			for (j = 0; j < n; j++)
				Max[i][j] = in.nextInt();
		do {
			flag = false;
			System.out.println("������������Ѿ��������Դ��[Allocation]:");
			for (i = 0; i < m; i++)
				for (j = 0; j < n; j++) {
					Allocation[i][j] = in.nextInt();
					if (Allocation[i][j] > Max[i][j])
						flag = true;
					Need[i][j] = Max[i][j] - Allocation[i][j];
					temp[j] += Allocation[i][j];// ͳ���Ѿ���������̵���Դ��Ŀ
				}
			System.out.println(flag);
			if (flag == true)
				System.out.println("�������Դ�������������������������!");
		}while (flag);
		
		for (j = 0; j < n; j++) {
			Available[j] = Available[j] - temp[j];// ʣ�µĿ�����Դ=��ʼ������Դ-�ѷ�����Դ
		}

	}

	/******** ��ʾ��Դ������� ********/
	public void showdata() {
		int i, j;
		System.out.println("*************************************************************");
		System.out.println("ϵͳĿǰ���õ���Դ[Available]:");
		for (i = 0; i < N; i++) // ���������Դ��
			System.out.print(Name[i]+" ");
		System.out.println();
		for (j = 0; j < N; j++)
			System.out.print(Available[j]+" ");// ���������Դ������
		System.out.println();
		System.out.println("ϵͳ��ǰ����Դ����������£�");
		System.out.println("        Max         Allocation    Need");
		System.out.print("������     ");
		for (j = 0; j < 3; j++) {
			for (i = 0; i < N; i++)
				System.out.print(Name[i]+" ");
			System.out.print("      ");
		}
		System.out.print("\n");
		for (i = 0; i < M; i++) {
			System.out.print("P" + i+"    ");
			for (j = 0; j < N; j++)
				System.out.print(Max[i][j]+" ");
			System.out.print("    ");
			for (j = 0; j < N; j++)
				System.out.print(Allocation[i][j]+" ");
			System.out.print("    ");
			for (j = 0; j < N; j++)
				System.out.print(Need[i][j]+" ");
			System.out.print("\n");
		}
	}
	/********���Է�����Դ********/
	public int test(int i)//������Դ����
	{
		int j;
		//for(j=0;j<M;j++)
		for(j=0;j<N;j++)
		{
			Available[j]=Available[j]-Request[j];
			Allocation[i][j]=Allocation[i][j]+Request[j];
			Need[i][j]=Need[i][j]-Request[j];
		}
		return 1;
	}
	/********��ȫ���㷨********/
	/*
	�ӵ�һ�����̿�ʼ��飬���Ƿ����н��̶�����˳���������
	M // ���̵������
	N // ��Դ�������
	*/
	int safe()
	{
		int i,j,k=0,m,apply;
		boolean[] Finish= new boolean[100];
		for(int x=0;x<100;x++) {
			Finish[x]=false;
		}
		//for (j=0;j<M;j++)
		for(j=0;j<N;j++)
	        Work[j]=Available[j];

		for(i=0;i<M;i++)
		{
			apply=0;
			for(j=0;j<N;j++)
			{
				if (Finish[i]==false&&Need[i][j]<=Work[j])
				{
					apply++;//������Դ���ͼ���

					if(apply==N)//�鿴���п�����Դ�Ƿ����ĳһ���̵�����
					{  //ֱ��ÿ����Դ��������С��ϵͳ��������Դ���ſɷ���
						for(m=0;m<N;m++)
					        Work[m]=Work[m]+Allocation[i][m];//i�Ž���ִ����ɣ��ͷ���Դ���ı�ɷ�����Դ��
						Finish[i]=true;
						Security[k]=i; //Security�б��氲ȫ����
						i=-1; //��֤ÿ�β�ѯ���ӵ�һ�����̿�ʼ
						k++;
						
					}
				}
			}
		}
		for(i=0;i<M;i++){
			if(Finish[i]==false){
				System.out.print("ϵͳ����ȫ\n");//���ɹ�ϵͳ����ȫ
				return -1;
			}
		}
		System.out.print("ϵͳ�ǰ�ȫ��!\n");//�����ȫ������ɹ�
		System.out.print("����һ����ȫ����:");
		for(i=0;i<M;i++){//������н�������
			System.out.print("P"+Security[i]);
			if(i<M-1)
				System.out.print("->");
		}
		System.out.print("\n");
		return 0;
	}

	/********�������м��㷨��������Դ�����Է���********/
	void bank()
	{
		char ch;
		int i,j;
		ch='y';
		System.out.print("���������������Դ�Ľ��̺�(0-"+(M-1)+"):");  //0---M-1
	    i=in.nextInt();//������������Դ�Ľ��̺�
	    System.out.print("���������P"+i+"Ҫ�������Դ����:\n");
		for(j=0;j<N;j++)
		{
			System.out.print(Name[j]);
			Request[j]=in.nextInt();//������Ҫ�������Դ
		}
	    for (j=0;j<N;j++){
			if(Request[j]>Need[i][j])//�ж������Ƿ�������������������
			{
				System.out.print("����P"+i+"�������Դ��������Ҫ����Դ");
				System.out.print(" ���䲻����������䣡\n");
				ch='n';
				break;
			}
			else {
	            if(Request[j]>Available[j])//�ж������Ƿ���ڵ�ǰ�ɷ�����Դ�������������
				{
	            	System.out.print("����"+i+"�������Դ����ϵͳ���ڿ����õ���Դ");
	            	System.out.print("\n");
	            	System.out.print(" ϵͳ�����㹻��Դ���������!\n");
					ch='n';
					break;
				}
			}
	    }
	   if(ch=='y') {
			test(i);//���ݽ����������任��Դ
			showdata();//���ݽ�����������ʾ�任�����Դ
			safe();//���ݽ����������������м��㷨�ж�
	    }
	}
	



}
