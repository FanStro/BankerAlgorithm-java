package BankerAlgorithm;

import java.util.Scanner;

public class BankerClass {
	
	String[] Name = new String[100]; // 资源的名称
	int[][] Max = new int[100][100];
	int[] Available = new int[100];// 可用资源矩阵
	int[][] Allocation = new int[100][100];// 系统已分配矩阵
	int[][] Need = new int[100][100];// 还需要资源矩阵
	int[] Request = new int[100];// 请求资源向量
	int[] Security = new int[100];// 存放安全序列
	int[] Work = new int[100];// 存放系统可提供资源
	int M = 100;// 进程的最大数
	int N = 100;// 资源的最大数

	Scanner in = new Scanner(System.in);

	/********
	 * 初始化数据：输入进程数量、资源种类、各种资源可利用数量、 各进程的资源已分配数量、各进程对资源最大需求量等。
	 ********/
	public void init() {
		/* m为进程个数，即矩阵行数，n为资源种类，即矩阵列数。 */
		int i, j, m, n;
		int number;
		boolean flag;
		String name;// 输入资源名称
		int[] temp = new int[100];// 统计已经分配的资源
		System.out.println("系统可用资源种类为:");
		n = in.nextInt();
		N = n;
		for (i = 0; i < n; i++)// 输入资源信息：资源名称及数量
		{
			System.out.println("资源" + i + "的名称:");
			name = in.next(); // 不能用%c,否则出错
			Name[i] = name;
			System.out.println("资源" + name + "的初始个数为:");
			number = in.nextInt();
			Available[i] = number;
		}
		System.out.print("\n");
		System.out.println("请输入进程的数量:");
		m = in.nextInt();
		M = m;
		System.out.println("请输入各进程的最大需求矩阵的值[Max]:");
		for (i = 0; i < m; i++)// 输入Max矩阵
			for (j = 0; j < n; j++)
				Max[i][j] = in.nextInt();
		do {
			flag = false;
			System.out.println("请输入各进程已经分配的资源量[Allocation]:");
			for (i = 0; i < m; i++)
				for (j = 0; j < n; j++) {
					Allocation[i][j] = in.nextInt();
					if (Allocation[i][j] > Max[i][j])
						flag = true;
					Need[i][j] = Max[i][j] - Allocation[i][j];
					temp[j] += Allocation[i][j];// 统计已经分配给进程的资源数目
				}
			System.out.println(flag);
			if (flag == true)
				System.out.println("分配的资源大于最大需求量，请重新输入!");
		}while (flag);
		
		for (j = 0; j < n; j++) {
			Available[j] = Available[j] - temp[j];// 剩下的可用资源=初始可用资源-已分配资源
		}

	}

	/******** 显示资源分配矩阵 ********/
	public void showdata() {
		int i, j;
		System.out.println("*************************************************************");
		System.out.println("系统目前可用的资源[Available]:");
		for (i = 0; i < N; i++) // 输出可用资源名
			System.out.print(Name[i]+" ");
		System.out.println();
		for (j = 0; j < N; j++)
			System.out.print(Available[j]+" ");// 输出可用资源的数量
		System.out.println();
		System.out.println("系统当前的资源分配情况如下：");
		System.out.println("        Max         Allocation    Need");
		System.out.print("进程名     ");
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
	/********尝试分配资源********/
	public int test(int i)//进行资源分配
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
	/********安全性算法********/
	/*
	从第一个进程开始检查，看是否所有进程都可以顺利运行完成
	M // 进程的最大数
	N // 资源的最大数
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
					apply++;//可用资源类型计数

					if(apply==N)//查看所有可用资源是否大于某一进程的需求
					{  //直到每类资源尚需数都小于系统可利用资源数才可分配
						for(m=0;m<N;m++)
					        Work[m]=Work[m]+Allocation[i][m];//i号进程执行完成，释放资源，改变可分配资源数
						Finish[i]=true;
						Security[k]=i; //Security中保存安全序列
						i=-1; //保证每次查询均从第一个进程开始
						k++;
						
					}
				}
			}
		}
		for(i=0;i<M;i++){
			if(Finish[i]==false){
				System.out.print("系统不安全\n");//不成功系统不安全
				return -1;
			}
		}
		System.out.print("系统是安全的!\n");//如果安全，输出成功
		System.out.print("存在一个安全序列:");
		for(i=0;i<M;i++){//输出运行进程数组
			System.out.print("P"+Security[i]);
			if(i<M-1)
				System.out.print("->");
		}
		System.out.print("\n");
		return 0;
	}

	/********利用银行家算法对申请资源进行试分配********/
	void bank()
	{
		char ch;
		int i,j;
		ch='y';
		System.out.print("请输入请求分配资源的进程号(0-"+(M-1)+"):");  //0---M-1
	    i=in.nextInt();//输入须申请资源的进程号
	    System.out.print("请输入进程P"+i+"要申请的资源个数:\n");
		for(j=0;j<N;j++)
		{
			System.out.print(Name[j]);
			Request[j]=in.nextInt();//输入需要申请的资源
		}
	    for (j=0;j<N;j++){
			if(Request[j]>Need[i][j])//判断申请是否大于需求，若大于则出错
			{
				System.out.print("进程P"+i+"申请的资源大于它需要的资源");
				System.out.print(" 分配不合理，不予分配！\n");
				ch='n';
				break;
			}
			else {
	            if(Request[j]>Available[j])//判断申请是否大于当前可分配资源，若大于则出错
				{
	            	System.out.print("进程"+i+"申请的资源大于系统现在可利用的资源");
	            	System.out.print("\n");
	            	System.out.print(" 系统尚无足够资源，不予分配!\n");
					ch='n';
					break;
				}
			}
	    }
	   if(ch=='y') {
			test(i);//根据进程需求量变换资源
			showdata();//根据进程需求量显示变换后的资源
			safe();//根据进程需求量进行银行家算法判断
	    }
	}
	



}
