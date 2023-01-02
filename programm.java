import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
//Пациент
class patient 
{
	private int id=0;
	private String name = "";
	private String diagnosis ="";
	private String state = "";
	public patient(int new_id, String new_patient_name, String new_diagnosis, String new_state)
	{
		id = new_id;
		name= new_patient_name;
		diagnosis=new_diagnosis;
		state=new_state;
	}

	public patient()
	{
		int id = 0;
		final String name = "";
		final String diagnosis = "";
		final String state = "";
	}
	public final void close()
	{

	}
	//Вывод строки
	public final void out(patient[] patients)
	{
		int i = 0;
		System.out.printf("|id |ФИО пациета             |Диагноз пациента|Состояние пациента|\n");
		while (patients[i]!=null&&patients[i].id > 0)
		{
			System.out.printf("|%3d|%24s|%16s|%18s|\n", patients[i].id, patients[i].name, patients[i].diagnosis, patients[i].state);
			i++;
		}
		System.out.printf("------------------------------------------------------------------\n");
	}
	//Ввод строки
	public final void input_patient(int total_lines, patient[] patients, String patientname, String illnessname, String statusname)
	{
		patients[total_lines - 1].id = total_lines;
		patients[total_lines - 1].name= patientname;
		patients[total_lines - 1].diagnosis= illnessname;
		patients[total_lines - 1].state= statusname;
	}

	//Вывод результатов в файл//
	public final void write_results(patient[] patients)
	{
		Path filePath=Paths.get("", "", "patients.txt");
		int i = 0;
		try{
		FileWriter writer = new FileWriter("patients.txt", true);
		Files.writeString(filePath,String.format("|id |ФИО пациета             |Диагноз пациента|Состояние пациента|\n"),StandardOpenOption.APPEND);
		while (patients[i].id > 0)
		{
			Files.writeString(filePath,String.format("|%3d|%24s|%16s|%18s|\n", patients[i].id, patients[i].name, patients[i].diagnosis, patients[i].state),StandardOpenOption.APPEND);
			i++;
		}
		Files.writeString(filePath,String.format("------------------------------------------------------------------\n"),StandardOpenOption.APPEND);
	}
	 catch (IOException e) {
            e.printStackTrace();
        }
	}
	//Удаление строки
	public final void patient_del(int line, patient[] patients)
	{
        int i = 0, checks= 0;
        while (patients[i].id > 0)
        {
            if (patients[i].id == line)
                {
                    checks= 1;
                }
                
            if (checks==1)
            {
                patients[i].name=patients[i+1].name;
                patients[i].diagnosis=patients[i+1].diagnosis;
                patients[i].state=patients[i+1].state;
                if(patients[i].id!=0)
                    patients[i].id = i + 1;
            }
            i++;
        }
    patients[i-1].id=0;
	}
}
//Болезнь
class sickness 
{
	private int id=0;
	private String diagnosis = "";
	private String explanation = "";
	public sickness(int new_id, String new_diagnosis, String new_explanation)
	{
		id = new_id;
		diagnosis= new_diagnosis;
		explanation= new_explanation;
	}

	public sickness()
	{
		int id = 0;
		final String diagnosis = "";
		final String explanation = "";
	}
	public final void close()
	{

	}
	//Удаление строки
	public final void ill_del(int line, sickness[] ill)
	{
 	   int i = 0, checks= 0;
       	    while (ill[i].id > 0)
        	{
           	   if (ill[i].id == line)
               	    {
                       checks= 1;
              	     }
                
           	    if (checks==1)
           	    {
                	ill[i].diagnosis=ill[i+1].diagnosis;
                	ill[i].explanation=ill[i+1].explanation;
                	if(ill[i].id!=0)
                   	   ill[i].id = i + 1;
          	     }
           	     i++;
       		 }

    	  ill[i-1].id=0;
	}		
	//Ввод строки
	public final void ill_in(int total_lines, sickness[] ill, String illnessname) throws IOException
	{
	    Scanner in = new Scanner(System.in,"ibm-866");
		int check = 0;
		int ill_end = 0;
			int i = 0;
			int ch=ill[i].diagnosis.length();
			while (ill[i]!=null&&ill[i].id > 0)
			{
				if (illnessname.compareToIgnoreCase (ill[i].diagnosis) == 0)
				{
					check = 1;
				}
				i++;
			}
			if(ill[total_lines - 1]!=null)
			   { 
			     ill[total_lines - 1].diagnosis= illnessname;
		             ill[total_lines - 1].id=total_lines;
			   }
			if (check == 0)
			{
				System.out.printf("Был введен новый диагноз, введите его описание:\n");
				do
				{
					    ill[total_lines - 1].explanation=in.nextLine();
					    ch=ill[total_lines - 1].explanation.length();
				} while (ch < 1);

			}
	}
	//Вывод
	public final void out_illness(int line, sickness[] ill)
	{
		for (int i = 0; i < 30; i++)
		{
			if (ill[i]!=null&&ill[i].id == line)
			{
				System.out.printf("%s - %s\n", ill[i].diagnosis, ill[i].explanation);
			}
		}
	}
}
//Доктор
class doctor 
{
	private int id=0;
	private String patient_name = "";
	private String doctor_name = "";
	
	public doctor()
	{
		int id = 0;
		final String patient_name = "";
		final String doctor_name = "";
	}


	public doctor(int new_id, String new_patient_name, String new_doctor_name)
	{
		id = new_id;
		patient_name= new_patient_name;
		doctor_name= new_doctor_name;
	}
	//Удаление строки
	public final void doc_del(int line, doctor[] doc)
	{
		int i = 0;
		int check = 0;
	while (doc[i].id > 0)
	{
		if (doc[i].id == line)
		{
			check = 1;
		}
		if (check==1)
		{
			doc[i]=doc[i + 1];
			 if(doc[i].id!=0)
    			doc[i].id =i+1;
		}
		i++;
	}
	doc[i -1].id=0;
	}
//// /////Двумерный массив ///////////
    public void doc_list_in(int line,doctor doc_list[][], String doctorname, String patientname)
    {
        int check = 1,i=0;
        for (int k = 0; k < line; k++)
        {
            if (doctorname.compareToIgnoreCase(doc_list[k][0].doctor_name) != 0 && doc_list[k][0].id<1&&check==1)
            {
                doc_list[k][0].id = 1;
                doc_list[k][0].doctor_name= doctorname;
                doc_list[k][0].patient_name= patientname;
		check=0;
            }
            else
            {
                if (doctorname.compareToIgnoreCase(doc_list[k][0].doctor_name) == 0)
                {
                    check = 0;
                    while (doc_list[k][i].id > 0)
                    {
                        i++;
                    }
                        doc_list[k][i].id = i + 1;
                        doc_list[k][i].doctor_name= "";
                        doc_list[k][i].patient_name= patientname;
                }
            }

        }
    }
   public  void doc_list_del(int total_lines,int line,doctor doc[],doctor doc_list[][])
    {
	int count=0;
        for (int k = 0; k < 30; k++)
        {
            for (int i = 0; i < 30; i++)
            {
                if (doc_list[k][i].patient_name.compareToIgnoreCase(doc[line-1].patient_name) == 0)
                {
		    if(k!=0)
                    	doc_list[k][i].id = 0;
                    doc_list[k][i].patient_name= "";
                }
		if(doc_list[k][i].patient_name.compareToIgnoreCase("")!=0)
			{
				count++;
				
			}		

            }
	    if(count==0)
	       {
		  doc_list[k][0].doctor_name= "";
		  doc_list[k][0].id = 0;
		}
        }
    }
   public  void doc_list_out(int line, doctor doc_list[][])
    {
        System.out.printf("_____________________________________________________\n");
        System.out.printf("|Врач                    |Пациент                   |\n");
        for (int k = 0; k < 30; k++)
        {
            
            for (int i = 0; i < 30; i++)
            {
                if (doc_list[k][i].id > 0)
                {
                    System.out.printf("|%24s|", doc_list[k][i].doctor_name);
                    System.out.printf("%24s  |\n", doc_list[k][i].patient_name);
                }
            }
            if(doc_list[k][0].id>0)
            System.out.printf("|________________________|__________________________|\n");
        }
        System.out.printf("\n\n\n");
    }
//////Конец операций над двумерным массивом/////
	public final void close()
	{

	}
	//Вывод в файл
	public final void write_doc(int i, doctor[] doc)
	{
	    Path filePath=Paths.get("", "", "staff.txt");
		try{
		FileWriter writer = new FileWriter("staff.txt", true);

		Files.writeString(filePath,String.format("|%24s ", doc[i].patient_name),StandardOpenOption.APPEND);

		}
		catch (IOException e) {
            e.printStackTrace();
        }

	}
	public final void input_doc(int total_lines, doctor[] doc, String doctorname, String patientname)
	{
		doc[total_lines - 1].id = total_lines - 1;
		doc[total_lines - 1].doctor_name=doctorname;
		doc[total_lines - 1].patient_name= patientname;
	}
	////Вывод данных о докторе//
public final void out_doc(int line, doctor[] doc)
{
	System.out.printf("|Пациент                  |Доктор                  |\n");
	System.out.printf("|%24s |%24s|\n",doc[line-1].patient_name,doc[line - 1].doctor_name);
	System.out.printf("----------------------------------------------------\n");
}
}
//Больница
class hospital 
{
	private int id=0;
	private String doctor_name ="";
	private String hospital_name = "";
	public hospital()
	{
		int id = 0;
		final String doctor_name = "";
		final String hospital_name = "";
	}
	public final void get_doctor_name(String new_doc_name)
	{
		doctor_name= new_doc_name;
	}
	public hospital(int new_id, String new_doctor_name, String new_hospital_name)
	{
		id = new_id;
		doctor_name= new_doctor_name;
		hospital_name= new_hospital_name;
	}
	//Ввод
	public final void input_hosp(int total_lines, hospital[] hosp, String doctorname, String hospitalname)
	{
		hosp[total_lines - 1].id = total_lines - 1;
		hosp[total_lines - 1].doctor_name= doctorname;
		hosp[total_lines - 1].hospital_name= hospitalname;
	}
	//вывод в файл
	public final void write_hosp(int i, hospital[] hosp)
	{
	    Path filePath=Paths.get("", "", "staff.txt");
		try{
		FileWriter writer = new FileWriter("staff.txt", true);

			Files.writeString(filePath,String.format("|%24s|%24s |\n", hosp[i].doctor_name,hosp[i].hospital_name),StandardOpenOption.APPEND);

		}
		catch (IOException e) {
            e.printStackTrace();
        }

	}

	public final void close()
	{

	}
	//Удаление строки
	public final void hosp_del(int line, hospital[] hosp)
	{
		int i = 0;
		int check = 0;
		while (hosp[i+1]!=null&&hosp[i]!=null&&hosp[i].id > 0)
		{
			if (hosp[i].id == line)
			{
				check = 1;
			}
			if (check != 0)
			{

				hosp[i]=hosp[i + 1];
				if(hosp[i].id!=0)
    				hosp[i].id =i+1;
			}
			i++;
		}
		hosp[i -1].id=0;
	}
	//Вывод данных о больнице//
public final void out_hospital(int line, hospital[] hosp)
{
	System.out.printf("|Доктор                  |Больница                 |\n");
	System.out.printf("|%24s|%24s|\n", hosp[line - 1].doctor_name, hosp[line - 1].hospital_name);
	System.out.printf("----------------------------------------------------\n");
}
}

//Состояние пациента
class status 
{
	private int id=0;
	private   String patient_status="";
	private   String status_description="";
	
	public status()
	{
		 int id=0;
		  String patient_status = "";
		   String status_description = "";
	}
	
	public status(int new_id, String new_patient_status, String new_status_description)
	{
		id = new_id;
		patient_status= new_patient_status;
		status_description= new_status_description;
	}

	//Вывод 
	public final void out_state(int line, status[] state)
	{
		  System.out.printf("%s - %s \n", state[line-1].patient_status, state[line-1].status_description);
	}
	//Ввод 
	public final void new_line(int total_lines, String statusname, status[] state)
	{
		int i;
		i=4;
		if (statusname.compareToIgnoreCase("Здоров") == 0)
		{
			i = 0;
		}
		 if (statusname.compareToIgnoreCase("Умеренное") == 0)
		 {
			 i = 1;
		 }
		 if (statusname.compareToIgnoreCase("Серьёзное") == 0)
		 {
			 i = 2;
		 }
		 switch (i)
		 {
		 case 0:
			 state[total_lines - 1] = state[30];
			 state[total_lines - 1].id = total_lines - 1;
			 break;
		 case 1:
			 state[total_lines - 1] = state[31];
			 state[total_lines - 1].id = total_lines - 1;
			 break;
		 case 2:
			 state[total_lines - 1] = state[32];
			 state[total_lines - 1].id = total_lines - 1;
			 break;
		 default:
			 break;
		 }

	}
	//Удаление строки
	public final void del_line(int line, status[] state)
	{
		int i = 0;
		int check = 0;
		while (state[i].id > 0 && state[i].id < 30)
		{
			if (state[i].id == line)
			{
				check = 1;
			}
			if (check != 0)
			{
				state[i]=state[i + 1];
				if(state[i].id!=0)
    				state[i].id =i+1;
			}
			i++;
		}
		state[i-1].id=0;
	}

	public final void close()
	{

	}
}

 class Main
{
	public static void main(String[] args) throws IOException
	{
		Scanner in = new Scanner(System.in,"ibm-866");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int line_num,ch;
		int total_lines = 1;
		int check_lines = 0;
		String patientname = new String(new char[40]);
		String doctorname = new String(new char[40]);
		String hospitalname = new String(new char[40]);
		String statusname = new String(new char[20]);
		String illnessname = new String(new char[40]);
		patient[] patients=new patient[30];
		patients[0] = new patient(1, "Алексей А.A.", "Грипп", "Здоров");
		status[] state = new status[33];
		for(int k=0;k<33;k++)
		{
		    switch(k)
		    {
    		    case 0:
    		        state[k] = new status(1, "Здоров", "Пациент здоров");
    		        break;
    		    case 30:
    		        state[k] = new status(30, "Здоров", "Пациент здоров");
    		        break;
    		    case 31:
    		        state[k] = new status(31, "Умеренное", "Не наблюдается тяжёлых ослажнений");
    		        break;
    		    case 32:
    		       	state[k] = new status(32, "Серьёзное", "Необходимо постоянное наблюдение и уход за пациентом");
    		        break;
    		    default:
    		        state[k] = new status();
    		         break;
		    }
		}
		hospital[] hosp = new hospital[30];
		hosp[0] = new hospital(1,"Борисов Ф.А.", "Городская поликлиника №12");
		doctor[] doc = new doctor[30];
		doctor[][] doc_list = new doctor[30][30];
		doc[0] = new doctor(1,"Александр И.И.","Борисов Ф.А.");
		sickness[] ill = new sickness[30];
		ill[0] = new sickness(1,"Грипп","Острое респираторное вирусное заболевание, вызываемое вирусами гриппа,поражающее верхние дыхательные пути");
		for(int k=1;k<30;k++)
		    patients[k] = new patient();
		for(int k=1;k<30;k++)
		    hosp[k] = new hospital();
		for(int k=1;k<30;k++)
		    doc[k] = new doctor();
    		for(int k=1;k<30;k++)
		    ill[k] = new sickness();
    		for(int k=0;k<30;k++)
		        for(int m=0;m<30;m++)
		            {
		                if(k==0&&m==0)
		                    doc_list[0][0] = new doctor(1,"Александр И.И.","Борисов Ф.А.");
		                else
		                    doc_list[k][m] = new doctor();
		                
		            
		            }
		int repeat = 1;
		do
		{
			check_lines = 0;
			patients[0].out(patients);
			System.out.print("Введите:\n1-для просмотра данных о пациенте\n2-для просмотра данных о болезни\n3-для просмотра состояния пациета\n4-для удаления строки\n5-для добавления строки\n6-Записать таблицу в файл\n7-закрыть программу\n8-Вывод докторов и всех их пациентов\n");
			int choice_patient;
			int choice_doctor;
			do
			{
				choice_patient=in.nextInt();
			} while (choice_patient > 8 || choice_patient < 1);
			switch (choice_patient)
			{
			   //просмотр данных о пациенте
			case 1:
				if (total_lines > 0)
				{
					System.out.print("Введите номер пациента:\n");
					do
					{
						if (check_lines != 0)
						{
							System.out.print("Повторите ввод\n");
						}
						
						line_num=in.nextInt();
						check_lines = 1;
					} while (line_num > total_lines || line_num < 1);
					doc[0].out_doc(line_num, doc);
					System.out.print("Введите:\n1-Просмотр информации о докторе\nДля возвращение к предыдущей таблице введите любое другое число\n");
					choice_doctor=in.nextInt();
					switch (choice_doctor)
					{
					case 1:
						hosp[0].out_hospital(line_num, hosp);
						break;
					default:
						break;
					}
				}
				else
				{
					System.out.print("Нет записей\n");
				}
				break;
				//просмотр данных о болезни
			case 2:
				if (total_lines > 0)
				{
					check_lines = 0;
					System.out.print("Введите номер пациента:\n");
					do
					{
						if (check_lines != 0)
						{
							System.out.print("Повторите ввод\n");
						}
						line_num=in.nextInt();
						check_lines = 1;
					} while (line_num > total_lines || line_num < 1);
					ill[0].out_illness(line_num, ill);
				}
				else
				{
					System.out.print("Нет записей\n");
				}
				break;
				//просмотр состояния пациета
			case 3:
				check_lines = 0;
				if (total_lines > 0)
				{
					System.out.print("Введите номер пациента:\n");
					do
					{
						if (check_lines != 0)
						{
							System.out.print("Повторите ввод\n");
						}
						line_num=in.nextInt();
						check_lines = 1;
				} while (line_num > total_lines || line_num < 1);
					state[0].out_state(line_num, state);

				}
				else
				{
					System.out.print("Нет записей\n");
				}

				break;
				//удаление строки
			case 4:
				if (total_lines > 0)
				{
					check_lines = 0;
					System.out.print("Введите номер строки для удаления:\n");
					do
					{
						if (check_lines != 0)
						{
							System.out.print("Повторите ввод\n");
						}
						line_num=in.nextInt();
						check_lines = 1;
					} while (line_num > total_lines || line_num < 1);
					patients[0].patient_del(line_num, patients);
					hosp[0].hosp_del(line_num, hosp);
					ill[0].ill_del(line_num,ill);
					doc_list[0][0].doc_list_del(total_lines,line_num,doc,doc_list);
					doc[0].doc_del(line_num, doc);
					state[0].del_line(line_num, state);
					total_lines--;
				}
				else
				{
					System.out.print("Нет записей для удаления\n");
				}
				break;
				//добавления строки
			case 5:
				total_lines+=1;
				in.nextLine();
				System.out.print("Введите ФИО пациента:\n");
					do
					{
						patientname=in.nextLine();
						ch=doctorname.length();
					} while (ch == 0);
					System.out.print("Введите ФИО лечащего доктора пациента:\n");
					do
					{
						doctorname=in.nextLine();
						ch=doctorname.length();
					} while (ch == 0);
					doc[0].input_doc(total_lines,doc,doctorname,patientname);
					doc_list[0][0].doc_list_in(total_lines,doc_list,doctorname,patientname);
					System.out.print("Введите название больницы доктора:\n");
					do
					{
						hospitalname=in.nextLine();
						ch=hospitalname.length();
					} while (ch == 0);
					hosp[0].input_hosp(total_lines, hosp, doctorname, hospitalname);
					System.out.print("Введите диагноз пациента:\n");
					do
					{
						illnessname=in.nextLine();
						ch=illnessname.length();
					} while (ch == 0);
					ill[0].ill_in(total_lines, ill, illnessname);
					System.out.print("Введите состояние пациента (Здоров, Умеренное, Серьёзное):\n");
					do
					{
						statusname=in.nextLine();
					} while (statusname.compareToIgnoreCase("Здоров") != 0 && statusname.compareToIgnoreCase("Умеренное") !=0 && statusname.compareToIgnoreCase("Серьёзное") != 0);
					state[0].new_line(total_lines,statusname,state);
					patients[0].input_patient(total_lines, patients, patientname, illnessname, statusname);
				break;
				//Записать таблицу в файл
			case 6:
			   
			   File r=new File("staff.txt");		
			   File w=new File("patients.txt");
			   r.delete();
			   w.delete();
			   w.createNewFile();		
			   r.createNewFile();
			   FileWriter pat = new FileWriter(r, true);
			   FileWriter staff = new FileWriter(w, true);
		           Path filePath=Paths.get("", "", "staff.txt");
			   patients[0].write_results(patients);
			   try{
				Files.writeString(filePath,String.format("|Пациент                  |Доктор                  |Больница                  |\n"),StandardOpenOption.APPEND);

			 	  for (int i = 0; i < total_lines; i++)
				   {
					   doc[0].write_doc(i, doc);
					   hosp[0].write_hosp(i, hosp);
				   }
				Files.writeString(filePath,String.format("-------------------------------------------------------------------------------\n"),StandardOpenOption.APPEND);
			}
			 catch (IOException e) 
				{
          			  e.printStackTrace();
				}

				break;
				//закрыть программу
			case 7:
				repeat = 0;
				break;
			case 8:///вывод списка врачей и их пациентов
				doc_list[0][0].doc_list_out(total_lines,doc_list);
			default:
				break;
			}
		} while (repeat != 0);
		in.close();
		patients = null;
		ill = null;
		state=null;
	}

}