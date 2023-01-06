#define _CRT_SECURE_NO_WARNINGS
#include <locale.h>
#include <stdio.h>
#include <atlalloc.h>
#include <conio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <iostream>
using namespace std;
class prepatient {
protected:
    int id;
    string name[25];
    string diagnosis[40];
    string state[20];
public:
    void empty_num(int lines)
    {
        cout << "Занятых мест "<<lines<<"\n";
    }
     int get_id()
     {
         return id;
     }
     string get_name()
     {
         return name->c_str();
     }
     string get_diagnosis()
     {
         return diagnosis->c_str();
     }
     string get_state()
     {
         return state->c_str();
     }
    prepatient()
    {

    }
    prepatient(int id, string patient_name, string diagnosis, string state)
    {
        this->id = id;//this пример
        this->name->assign(patient_name);
        this->diagnosis->assign(diagnosis);
        this->state->assign(state);
    }
    ////// Чистая Виртуальная функция 
    virtual void empty_num(int lines, int* empty) = 0;
};
//Пациент//
class patient:public prepatient
{
private:

    static int Count;//статичное поле

public:
    /////Перегрузка операторов(объявление)
    patient operator++();
    patient operator++(int);
    patient operator+(const patient  & other);
    
    ///////////////////////////
    patient operator=(prepatient& pat)
    {
        this->id = pat.get_id();
        this->name->assign(pat.get_name());
        this->diagnosis->assign(pat.get_diagnosis());
        this->state->assign(pat.get_state());
    }
    void empty_num(int lines,int *empty)
    {
        *empty = 30 - lines;
    }
    ///////////////////////////
    int* setcount(int line)
    {
        Count = line;
        return &Count;
    }
    int& getcount()
    {
        return Count;
    }
    ////////Конструктор вызывает конструктор базового класса
    patient(int id, string patient_name, string diagnosis, string state)
        :prepatient( id, patient_name,  diagnosis, state)
    {

    }
    void out(patient patients[30])
    {
        int i = 0;
        cout << "|id |ФИО пациета             |Диагноз пациента|Состояние пациента|\n";
        while (patients[i].id > 0)
        {
            cout << "|" << patients[i].id << "  |" << patients[i].name->c_str() << "           |" << patients[i].diagnosis->c_str() << "           |" << patients[i].state->c_str() << "            |\n";
            i++;
        }
        cout<<"------------------------------------------------------------------\n";
    }
    patient()
    {
        int id = 0;
        string name = "";
        string diagnosis = "";
        string state = "";
    }
    ~patient()
    {

    }

    void input_patient(int total_lines, patient patients[30], string patientname, string illnessname, string statusname)
    {
        patients[total_lines - 1].id = total_lines;
        patients[total_lines - 1].name->assign(patientname);
        patients[total_lines - 1].diagnosis->assign(illnessname);
        patients[total_lines - 1].state->assign(statusname);
    }

    //Вывод результатов в файл//
    void write_results(patient patients[30])
    {
        FILE* dev;
        dev = fopen("patients.txt", "w+");
        int i = 0;
        fprintf(dev, "|id |ФИО пациета             |Диагноз пациента|Состояние пациента|\n");
        while (patients[i].id > 0)
        {
            fprintf(dev, "|%3d|%24s|%16s|%18s|\n", patients[i].id, patients[i].name->c_str(), patients[i].diagnosis->c_str(), patients[i].state->c_str());
            i++;
        }
        fprintf(dev, "------------------------------------------------------------------\n");
        fclose(dev);
    }

    void patient_del(int line, patient patients[30])
    {
        int i = 0, check = 0;
        while (patients[i].id > 0)
        {
            if (patients[i].id == line)
                check = 1;
            if (check)
            {
                patients[i] = patients[i + 1];
                patients[i].id = i + 1;
            }
            i++;
        }
        patients[i - 1].id = 0;
    }
};
int patient::Count;

/////Перегрузка операторов
patient patient::operator++()
{
    this->id++;
    return(*this);
}
patient patient::operator++(int)
{
    patient temp;
    temp= *this;
    ++this->id;
    return temp;
}
patient patient::operator+(const patient  &other)
{
    patient temp;
    temp.id = this->id + other.id;
    return(temp);
}


///////////////////
//Болезнь//
class sickness {
private:
    int id;
    string diagnosis;
    string explanation;
public:
    sickness(int new_id, string new_diagnosis, string new_explanation)
    {
        id = new_id;
        diagnosis=new_diagnosis;
        explanation=new_explanation;
    }
    sickness()
    {
        int id = 0;
        string diagnosis = "";
        string explanation = "";
    }
    ~sickness()
    {

    }
    void ill_in(int total_lines, sickness ill[30], string illnessname)
    {
        int check = 0, ill_end = 0;
        ill[total_lines - 1].id = total_lines;
        int i = 0;
        while (ill[i].diagnosis.length() > 0)
        {
            if (illnessname.compare(ill[i].diagnosis) == 0)
                check = 1;
            i++;
        }
        ill[total_lines - 1].diagnosis.compare(illnessname);
        if (check == 0)
        {
            cout << "Был введен новый диагноз, введите его описание:\n";
            do {
                getline(cin,ill[total_lines - 1].explanation);
            } while (ill[total_lines - 1].explanation.length() < 1);
        }
    }
    void out_illness(int line, sickness ill[30])
    {
        for (int i = 0; i < 30; i++)
        {
            if (ill[i].id == line)
                printf("%s - %s\n", ill[i].diagnosis.c_str(), ill[i].explanation.c_str());
        }
    }
};

class doctor {
private:
    int id;
     string patient_name;
    string doctor_name;
public:
    doctor()
    {
        int id = 0;
        char patient_name[40] = "";
        char doctor_name[40] = "";
    }

    doctor(int new_id, string new_patient_name, string new_doctor_name)
    {
        id = new_id;
        patient_name.assign(new_patient_name);
        doctor_name.assign(new_doctor_name);
    }

    void doc_del(int line, doctor doc[30])
    {
        int i = 0, check = 0;
        while (doc[i].id > 0)
        {
            if (doc[i].id == line)
                check = 1;
            if (check)
            {
                doc[i] = doc[i + 1];
                doc[i].id = i + 1;
            }
            i++;
        }
        doc[i - 1].id = 0;
    }

    ~doctor()
    {

    }
    void write_doc(int i, doctor doc[30])
    {
        FILE* ved;
        ved = fopen("staff.txt", "a+");
        fprintf(ved, "|%24s ", doc[i].patient_name.c_str());
        fclose(ved);
    }
    void input_doc(int total_lines, doctor doc[30], string doctorname, string patientname)
    {
        doc[total_lines - 1].id = total_lines - 1;
        doc[total_lines - 1].doctor_name= doctorname;
        doc[total_lines - 1].patient_name= patientname;
    }
    ////Вывод данных о докторе//
    void out_doc(int line, doctor doc[30])
    {
        cout << "|Пациент                  |Доктор                  |\n";
        cout << "|"<<doc[line - 1].patient_name.c_str()<<"           |"<< doc[line - 1].doctor_name.c_str()<<"            |\n";
        cout << "----------------------------------------------------\n";
    }
};

class hospital {
private:
    int id;
    string doctor_name;
    string hospital_name;
public:
    hospital()
    {
        int id = 0;
        string doctor_name = "";
        string hospital_name = "";
    }
    void get_doctor_name(string new_doc_name)
    {
        doctor_name= new_doc_name;
    }
    hospital(int new_id, string new_doctor_name, string new_hospital_name)
    {
        id = new_id;
        doctor_name.assign(new_doctor_name);
        hospital_name.assign(new_hospital_name);
    }
    void input_hosp(int total_lines, hospital hosp[30], string doctorname, string hospitalname)
    {
        hosp[total_lines - 1].id = total_lines - 1;
        hosp[total_lines - 1].doctor_name= doctorname;
        hosp[total_lines - 1].hospital_name= hospitalname;
    }
    void write_hosp(int i, hospital hosp[30])
    {
        FILE* ved;
        ved = fopen("staff.txt", "a+");
        fprintf(ved, "|%24s|%24s |\n", hosp[i].doctor_name.c_str(), hosp[i].hospital_name.c_str());
        fclose(ved);
    }

    ~hospital()
    {

    }
    void hosp_del(int line, hospital hosp[30])
    {
        int i = 0, check = 0;
        while (hosp[i].id > 0)
        {
            if (hosp[i].id == line)
                check = 1;
            if (check)
            {
                hosp[i] = hosp[i + 1];
                hosp[i].id = i + 1;
            }
            i++;
        }
        hosp[i - 1].id = 0;
    }
    //Вывод данных о больнице//
    void out_hospital(int line, hospital hosp[30])
    {
        cout << "|Доктор                  |Больница                 |\n";
        cout << "|"<< hosp[line - 1].doctor_name.c_str()<<"          | "<<hosp[line - 1].hospital_name.c_str()<<"          |\n";
        cout << "----------------------------------------------------\n";
    }
};
template <typename S>
class status {
private:
    S id;
    string patient_status = "";
    string status_description = "";
public:
    status(int new_id, string new_status, string new_description)
    {
        S id = new_id;
        patient_status= new_status;
        status_description= new_description;
    }
    status()
    {
        S id;
        string patient_status = "";
        string status_description = "";
    }
    void out_state(int line, status state[30])
    {
        cout << ""<< state[line - 1].patient_status.c_str()<<" - "<< state[line - 1].status_description.c_str()<<"\n";
    }
    void new_line(int total_lines, string statusname, status state[33])
    {
        int i;
        if (statusname.compare("Здоров") == 0)
            i = 0;
        if (statusname.compare("Умеренное") == 0)
            i = 1;
        if (statusname.compare("Серьёзное") == 0)
            i = 2;
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
    void del_line(int line, status state[33])
    {
        int i = 0, check = 0;
        while (state[i].id > 0 && state[i].id < 30)
        {
            if (state[i].id == line)
                check = 1;
            if (check)
            {
                state[i] = state[i + 1];
                state[i].id = i + 1;
            }
            i++;
        }
        state[i - 1].id = 0;
    }

    ~status()
    {

    }
};

int main()
{
    SetConsoleCP(1251);
    SetConsoleOutputCP(1251);
    char* locale = setlocale(LC_ALL, "RUS");
    int line_num, total_lines = 1, check_lines = 0;
    string patientname, doctorname, hospitalname, statusname, illnessname;
    patient patients[30];
    string str1 = "Александр И.И";
    string str2 = "Грипп";
    string str3 = "Здоров";
    patients[0] = {1, str1, str2, str3 };
    status<int>* state = new status<int>[33]{ };
    state[32] = { 32,"Серьёзное", "Необходимо постоянное наблюдение и уход за пациентом" };
    state[31] = { 31,"Умеренное","Не наблюдается тяжёлых ослажнений" };
    state[30] = { 30,"Здоров","Пациент здоров" };
    state[0] = { 1,"Здоров","Пациент здоров" };
    hospital  hosp[30];
    hosp[0] = { 1,"Борисов Ф.А.", "Городская поликлиника №12" };
    doctor doc[30];
    doc[0] = { 1,"Александр И.И.","Борисов Ф.А." };
    sickness* ill = new sickness[30]{};
    str1 = "Грипп";
    str2 ="Острое респираторное вирусное заболевание, вызываемое вирусами гриппа,поражающее верхние дыхательные пути";
    ill[0] = { 1,str1,str2};
    prepatient* pat =&patients[0];
    int repeat = 1;
    do {
        check_lines = 0;
        patients->out(patients);
        cout << "Введите:\n1-для просмотра данных о пациенте\n2-для просмотра данных о болезни\n3-для просмотра состояния пациета\n4-для удаления строки\n5-для добавления строки\n6-Записать таблицу в файл\n7-закрыть программу\n8-показать кол-во свободных мест\n9-количество занятых мест\n";
        int choice_patient, choice_doctor;
        do {
            scanf_s("%d", &choice_patient);
        } while (choice_patient > 9 || choice_patient < 1);
        switch (choice_patient)
        {
            //просмотр данных о пациенте
        case 1:
            if (total_lines > 0)
            {
                cout << "Введите номер пациента:\n";
                do {
                    if (check_lines)
                        cout << "Повторите ввод\n";
                    scanf_s("%d", &line_num);
                    check_lines = 1;
                } while (line_num > total_lines || line_num < 1);
                doc->out_doc(line_num, doc);
                cout << "Введите:\n1-Просмотр информации о докторе\nДля возвращение к предыдущей таблице нажмите любую другую кнопку\n";
                scanf_s("%d", &choice_doctor);
                switch (choice_doctor)
                {
                case 1:
                    hosp->out_hospital(line_num, hosp);
                    cout << "Нажмите любую кнопку для возвращения в главную таблицу\n";
                    _getch();
                    break;
                default:
                    break;
                }
            }
            else
                cout << "Нет записей\n";
            break;
            //просмотр данных о болезни
        case 2:
            if (total_lines > 0)
            {
                check_lines = 0;
                cout << "Введите номер пациента:\n";
                do {
                    if (check_lines)
                        cout << "Повторите ввод\n";
                    scanf_s("%d", &line_num);
                    check_lines = 1;
                } while (line_num > total_lines || line_num < 1);
                ill->out_illness(line_num, ill);
                cout << "Нажмите любую кнопку для возвращения в главную таблицу\n";
                _getch();
            }
            else
                cout << "Нет записей\n";
            break;
            //просмотр состояния пациета
        case 3:
            check_lines = 0;
            if (total_lines > 0)
            {
                cout << "Введите номер пациента:\n";
                do {
                    if (check_lines)
                        cout << "Повторите ввод\n";
                    scanf_s("%d", &line_num);
                    check_lines = 1;
                } while (line_num > total_lines || line_num < 1);
                state->out_state(line_num, state);
                cout << "Нажмите любую кнопку для возвращения в главную таблицу\n";
                _getch();
            }
            else
                cout << "Нет записей\n";

            break;
            //удаление строки
        case 4:
            if (total_lines > 0)
            {
                check_lines = 0;
                cout << "Введите номер строки для удаления:\n";
                do {
                    if (check_lines)
                        cout << "Повторите ввод\n";
                    scanf_s("%d", &line_num);
                    check_lines = 1;
                } while (line_num > total_lines || line_num < 1);
                patients->patient_del(line_num, patients);
                hosp->hosp_del(line_num, hosp);
                doc->doc_del(line_num, doc);
                state->del_line(line_num, state);
                total_lines--;
            }
            else
                cout << "Нет записей для удаления\n";
            break;
            //добавления строки
        case 5:
            total_lines++;
            cout << "Введите ФИО пациента:\n";
            do {
                getline(cin,patientname);
            } while (patientname.length() == 0);
            cout << "Введите ФИО лечащего доктора пациента:\n";
            do {
                getline(cin, doctorname);
            } while (doctorname.length() == 0);
            doc->input_doc(total_lines, doc, doctorname, patientname);
            cout << "Введите название больницы доктора:\n";
            do {
                getline(cin, hospitalname);
            } while (hospitalname.length() == 0);
            hosp->input_hosp(total_lines, hosp, doctorname, hospitalname);
            cout << "Введите диагноз пациента:\n";
            do {
                getline(cin, illnessname);
            } while (illnessname.length() == 0);
            ill->ill_in(total_lines, ill, illnessname);
            cout << "Введите состояние пациента (Здоров, Умеренное, Серьёзное):\n";
            do {
                getline(cin, statusname);
            } while (statusname.compare("Здоров") != 0 && statusname.compare("Умеренное") != 0 && statusname.compare("Серьёзное") != 0);
            state->new_line(total_lines, statusname, state);
            patients->input_patient(total_lines, patients, patientname, illnessname, statusname);
            break;
            //Записать таблицу в файл
        case 6:
            patients->write_results(patients);
            FILE* ved;
            ved = fopen("staff.txt", "w+");
            fprintf(ved, "|Пациент                  |Доктор                  |Больница                 |\n");
            fclose(ved);
            for (int i = 0; i < total_lines; i++)
            {
                doc->write_doc(i, doc);
                hosp->write_hosp(i, hosp);
            }
            ved = fopen("staff.txt", "a+");
            fprintf(ved, "------------------------------------------------------------------------------\n");
            fclose(ved);

            break;
            //закрыть программу
        case 7:
            repeat = 0;
            break;
        case 8:
            int num;
            int *res;
            res = (int*)malloc(sizeof(int));
            pat->empty_num(total_lines,&num);
            res=patients->setcount(num);
            *res=patients->getcount();
            cout << "Свободных мест:"<< *res<<"\n";
            break;
        case 9:
            int pre;
            patients->prepatient::empty_num(total_lines);
            break;
        default:
            break;
        }
    } while (repeat);
    delete[] patients;
    delete[] ill;
    delete[] state;
}