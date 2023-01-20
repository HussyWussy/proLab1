#include <stdio.h>
#include <locale.h>
#include <dirent.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
void recursiveDosyaAramaVeAcma(char *kutuphaneAdi,char aranacakKelime[],char etiketDizisi[][100])
{
    FILE *dosya;
    DIR *kutuphane = opendir(kutuphaneAdi);
    char yol[100];
    char *yazi;
    int boyut;
    char *aranacakKelimePointeri;
    int aranacakKelimeSayaci=0;
    if(kutuphane == NULL)
        return ;
    struct dirent* direntSeysi;
    direntSeysi = readdir(kutuphane);
    if(direntSeysi == NULL)
    {
        printf("Herhangi bir klasör açýlamadý\n");
        return;
    }
    while((direntSeysi=readdir(kutuphane))!=NULL)
    {
  
        if(strcmp(direntSeysi->d_name,".") !=0 && strcmp(direntSeysi->d_name,"..")!=0 && strcmp(direntSeysi->d_name,"...") !=0 && strcmp(direntSeysi->d_name,"output.txt") !=0 )
        {

            if(strstr(direntSeysi->d_name,".txt")!=0)
            {
                char acilacakDosyaYolu[100];
                strcpy(acilacakDosyaYolu,kutuphaneAdi);
                strcat(acilacakDosyaYolu,"/");
                strcat(acilacakDosyaYolu,direntSeysi->d_name);
                dosya = fopen(acilacakDosyaYolu,"r");
                if(dosya == NULL)
                {
                    printf("Dosya aï¿½?lamad?!\n");
                    fclose(dosya);
                    return;
                }
                else
                {
                    fseek(dosya,0,SEEK_END);
                    boyut = ftell(dosya);
                    if(boyut == 0)
                        {
                            continue;
                        }
                    else
                        fseek(dosya,0,SEEK_SET);
                    
                }
                yazi = (char*)calloc(boyut,1);
                if(yazi==NULL)
                    return; 

                fread(yazi,1,boyut,dosya);
                //printf(yazi);
                //printf("\n");
                aranacakKelimePointeri=strstr(yazi,aranacakKelime);
                while (aranacakKelimePointeri != NULL)
                {
                    aranacakKelimeSayaci++;
                    aranacakKelimePointeri=strstr(aranacakKelimePointeri+1,aranacakKelime);
                }
                
                
                printf("\n%s de %s den %d kere var\n",direntSeysi->d_name,aranacakKelime,aranacakKelimeSayaci);
                aranacakKelimeSayaci=0;
                fclose(dosya);
            }
            
            strcpy(yol,kutuphaneAdi);
            strcat(yol,"/");
            strcat(yol,direntSeysi->d_name);
            recursiveDosyaAramaVeAcma(yol,aranacakKelime,etiketDizisi);
        }
    }
    closedir(kutuphane);
}
void etiketDegistirme(char *kutuphaneAdi,char etiketDizisi[][100],char degistiktenSonrakiEtiket[],int degistirmeSecim)
{
    char degisecekEtiket[100];
    strcpy(degisecekEtiket,etiketDizisi[degistirmeSecim]);
    FILE *dosya;
    DIR *kutuphane = opendir(kutuphaneAdi);
    char yol[100];
    char *yazi;
    int boyut;
    char *etiketDegistirmePtr;
    char *yeniYaziPtr;
    char *etiketOncesiYazi;
    char *etiketSonrasiYazi;
    char *sonyazi;
    char *aramaPtrBas;
    char *aramaPtrSon;
    char *sonParantezPtr;
    char *etiket;
    int kontrol=0;
    if(kutuphane == NULL)
        return ;
    struct dirent* direntSeysi;
    direntSeysi = readdir(kutuphane);
    if(direntSeysi == NULL)
    {
        printf("Herhangi bir klasör açýlamadý\n");
        return;
    }
    while((direntSeysi=readdir(kutuphane))!=NULL)
    {
  
        if(strcmp(direntSeysi->d_name,".") !=0 && strcmp(direntSeysi->d_name,"..")!=0 && strcmp(direntSeysi->d_name,"...") !=0 && strcmp(direntSeysi->d_name,"output.txt") !=0 )
        {

            if(strstr(direntSeysi->d_name,".txt")!=0)
            {
                char acilacakDosyaYolu[100];
                strcpy(acilacakDosyaYolu,kutuphaneAdi);
                strcat(acilacakDosyaYolu,"/");
                strcat(acilacakDosyaYolu,direntSeysi->d_name);
                dosya = fopen(acilacakDosyaYolu,"r");
                if(dosya == NULL)
                {
                    printf("Dosya açýlamadý!\n");
                    fclose(dosya);
                    return;
                }
                else
                {
                    fseek(dosya,0,SEEK_END);
                    boyut = ftell(dosya);
                    if(boyut == 0)
                        {
                            continue;
                        }
                    else
                        fseek(dosya,0,SEEK_SET);
                    
                }
                yazi = (char*)calloc(boyut,1);
                etiketOncesiYazi = (char*)calloc(boyut+strlen(degistiktenSonrakiEtiket)-strlen(degisecekEtiket),1);
                yeniYaziPtr = (char*)calloc(boyut+strlen(degistiktenSonrakiEtiket)-strlen(degisecekEtiket),1);
                etiketSonrasiYazi = (char*)calloc(boyut+strlen(degistiktenSonrakiEtiket)-strlen(degisecekEtiket),1);
                sonyazi = (char*)calloc(boyut+strlen(degistiktenSonrakiEtiket)-strlen(degisecekEtiket),1);
                if(yazi==NULL)
                    return; 
                fread(yazi,1,boyut,dosya);
                fclose(dosya);
                aramaPtrBas = strstr(yazi,"[[");
                while(aramaPtrBas!=NULL && sonParantezPtr!= NULL)
                {
                    aramaPtrSon=strstr(aramaPtrBas,"]]");
                    sonParantezPtr = strchr(aramaPtrBas,']');
                    
                        while(sonParantezPtr!=aramaPtrSon)
                        {
                            aramaPtrBas = strstr(aramaPtrBas+2,"[[");
                            if(aramaPtrBas==NULL)
                                break;
                            sonParantezPtr=(strchr(aramaPtrBas,']'));
                        }  
                    
                    if(aramaPtrSon && aramaPtrBas!=NULL)
                    {
                        aramaPtrBas = aramaPtrBas + 2;
                        etiket = (char*)malloc(aramaPtrSon-aramaPtrBas+1);
                        memcpy(etiket,aramaPtrBas,aramaPtrSon - aramaPtrBas);
                        etiket[aramaPtrSon-aramaPtrBas]='\0';
                        if(strcmp(etiket,degisecekEtiket)==0)
                        {
                            etiketDegistirmePtr = strstr(yazi,degisecekEtiket);
                            if(etiketDegistirmePtr!=NULL)
                            {
                                fopen(acilacakDosyaYolu,"w");
                                strncpy(etiketOncesiYazi,yazi,etiketDegistirmePtr-yazi);
                                strcpy(etiketSonrasiYazi,etiketDegistirmePtr+strlen(degisecekEtiket));
                                strcpy(yeniYaziPtr,degistiktenSonrakiEtiket);
                                strcat(yeniYaziPtr,etiketSonrasiYazi);
                                strcat(etiketOncesiYazi,yeniYaziPtr);
                                fwrite(etiketOncesiYazi,1,strlen(etiketOncesiYazi),dosya);
                                fseek(dosya,0,SEEK_SET);
                                fseek(dosya,0,SEEK_END);
                                boyut = ftell(dosya);
                                if(boyut == 0)
                                    {
                                        continue;
                                    }
                                else
                                    fseek(dosya,0,SEEK_SET);
                                
                                fread(yazi,1,boyut,dosya);
                                etiketDegistirmePtr = strstr(etiketOncesiYazi+1,degisecekEtiket);
                                etiketOncesiYazi = (char*)calloc(boyut+strlen(degistiktenSonrakiEtiket)-strlen(degisecekEtiket),1);
                                yeniYaziPtr = (char*)calloc(boyut+strlen(degistiktenSonrakiEtiket)-strlen(degisecekEtiket),1);
                                etiketSonrasiYazi = (char*)calloc(boyut+strlen(degistiktenSonrakiEtiket)-strlen(degisecekEtiket),1);
                                sonyazi = (char*)calloc(boyut+strlen(degistiktenSonrakiEtiket)-strlen(degisecekEtiket),1);
                                
                            }
                            fclose(dosya);
                        }
                        aramaPtrBas = strstr(aramaPtrBas+2,"[[");
                    }
                    else
                        break;

                
                }
                fclose(dosya);
                }
            
            strcpy(yol,kutuphaneAdi);
            strcat(yol,"/");
            strcat(yol,direntSeysi->d_name);
            etiketDegistirme(yol,etiketDizisi,degistiktenSonrakiEtiket,degistirmeSecim);
        }
    }
    closedir(kutuphane);
}
void dosyaDegistirme(char *kutuphaneAdi,char etiketDizisi[][100],char degistiktenSonrakiEtiket[],int degistirmeSecim)
{
    char degisecekEtiket[100];
    strcpy(degisecekEtiket,etiketDizisi[degistirmeSecim]);
    FILE *dosya;
    DIR *kutuphane = opendir(kutuphaneAdi);
    char yol[100];
    char *yazi;
    int boyut;
    char *etiketDegistirmePtr;
    char *yeniYaziPtr;
    char *etiketOncesiYazi;
    char *etiketSonrasiYazi;
    char *sonyazi;
    char *aramaPtrBas;
    char *aramaPtrSon;
    char *sonParantezPtr;
    char *etiket;
    int kontrol=0;
    if(kutuphane == NULL)
        return ;
    struct dirent* direntSeysi;
    direntSeysi = readdir(kutuphane);
    if(direntSeysi == NULL)
    {
        printf("Herhangi bir klasör açýlamadý\n");
        return;
    }
    while((direntSeysi=readdir(kutuphane))!=NULL)
    {
  
        if(strcmp(direntSeysi->d_name,".") !=0 && strcmp(direntSeysi->d_name,"..")!=0 && strcmp(direntSeysi->d_name,"...") !=0 && strcmp(direntSeysi->d_name,"output.txt") !=0 )
        {

            if(strstr(direntSeysi->d_name,".txt")!=0)
            {
                char acilacakDosyaYolu[100];
                char temp[100];
                strcpy(temp,degisecekEtiket);
                strcat(temp,".txt");
                char temptemp[100];
                char yeniDosya[100];
                strcpy(temptemp,degistiktenSonrakiEtiket);
                strcat(temptemp,".txt");
                strcpy(acilacakDosyaYolu,kutuphaneAdi);
                strcat(acilacakDosyaYolu,"/");
                strcpy(yeniDosya,acilacakDosyaYolu);
                strcat(yeniDosya,temptemp);
                strcat(acilacakDosyaYolu,direntSeysi->d_name);
                dosya = fopen(acilacakDosyaYolu,"r");
                if(dosya == NULL)
                {
                    printf("Dosya açýlamadý!\n");
                    fclose(dosya);
                    return;
                }
                else
                {
                    fseek(dosya,0,SEEK_END);
                    boyut = ftell(dosya);
                    if(boyut == 0)
                        {
                            continue;
                        }
                    else
                        fseek(dosya,0,SEEK_SET);
                    
                }
                
                if(strcmp(direntSeysi->d_name,temp)==0)
                {
                    fclose(dosya);
                    rename(acilacakDosyaYolu,yeniDosya);
                    strcpy(direntSeysi->d_name,temptemp);
                }



                
                fclose(dosya);
                }
            
            strcpy(yol,kutuphaneAdi);
            strcat(yol,"/");
            strcat(yol,direntSeysi->d_name);
            dosyaDegistirme(yol,etiketDizisi,degistiktenSonrakiEtiket,degistirmeSecim);
        }
    }
    closedir(kutuphane);
}
void etiketArama(char *kutuphaneAdi,char etiketDizisi[][100],int aramaSecim)
{
    char aranacakEtiket[100];
    strcpy(aranacakEtiket,etiketDizisi[aramaSecim]);
    FILE *dosya;
    DIR *kutuphane = opendir(kutuphaneAdi);
    char yol[100];
    char *yazi;
    int boyut;
    char *etiketDegistirmePtr;
    char *yeniYaziPtr;
    char *etiketOncesiYazi;
    char *etiketSonrasiYazi;
    char *sonyazi;
    char *aramaPtrBas;
    char *aramaPtrSon;
    char *sonParantezPtr;
    char *etiket;
    int kontrol=0;
    if(kutuphane == NULL)
        return ;
    struct dirent* direntSeysi;
    direntSeysi = readdir(kutuphane);
    if(direntSeysi == NULL)
    {
        printf("Herhangi bir klasör açýlamadý\n");
        return;
    }
    while((direntSeysi=readdir(kutuphane))!=NULL)
    {
  
        if(strcmp(direntSeysi->d_name,".") !=0 && strcmp(direntSeysi->d_name,"..")!=0 && strcmp(direntSeysi->d_name,"...") !=0 && strcmp(direntSeysi->d_name,"output.txt") !=0 )
        {

            if(strstr(direntSeysi->d_name,".txt")!=0)
            {
                int sayac=0;
                char acilacakDosyaYolu[100];
                strcpy(acilacakDosyaYolu,kutuphaneAdi);
                strcat(acilacakDosyaYolu,"/");
                strcat(acilacakDosyaYolu,direntSeysi->d_name);
                dosya = fopen(acilacakDosyaYolu,"r");
                if(dosya == NULL)
                {
                    printf("Dosya açýlamadý!\n");
                    fclose(dosya);
                    return;
                }
                else
                {
                    fseek(dosya,0,SEEK_END);
                    boyut = ftell(dosya);
                    if(boyut == 0)
                        {
                            continue;
                        }
                    else
                        fseek(dosya,0,SEEK_SET);
                    
                }
                yazi = (char*)calloc(boyut,1);
                if(yazi==NULL)
                    return; 
                fread(yazi,1,boyut,dosya);
                fclose(dosya);
                aramaPtrBas = strstr(yazi,"[[");
                while(aramaPtrBas!=NULL && sonParantezPtr!= NULL)
                {
                    
                    aramaPtrSon=strstr(aramaPtrBas,"]]");
                    sonParantezPtr = strchr(aramaPtrBas,']');
                    
                        while(sonParantezPtr!=aramaPtrSon)
                        {
                            aramaPtrBas = strstr(aramaPtrBas+2,"[[");
                            if(aramaPtrBas==NULL)
                                break;
                            sonParantezPtr=(strchr(aramaPtrBas,']'));
                        }  
                    
                    if(aramaPtrSon && aramaPtrBas!=NULL)
                    {
                        
                        aramaPtrBas = aramaPtrBas + 2;
                        etiket = (char*)malloc(aramaPtrSon-aramaPtrBas+1);
                        memcpy(etiket,aramaPtrBas,aramaPtrSon - aramaPtrBas);
                        etiket[aramaPtrSon-aramaPtrBas]='\0';
                        if(strcmp(etiket,aranacakEtiket)==0)
                        {
                            sayac++;
                        }
                        aramaPtrBas = strstr(aramaPtrBas+2,"[[");
                    }
                    else
                        break;

                
                }
                printf("%s de %s den %d kere geçmiþ\n\n",direntSeysi->d_name,aranacakEtiket,sayac);
                fclose(dosya);
                }
            
            strcpy(yol,kutuphaneAdi);
            strcat(yol,"/");
            strcat(yol,direntSeysi->d_name);
            etiketArama(yol,etiketDizisi,aramaSecim);
        }
    }
    closedir(kutuphane);
}
void etiketTarama(char *kutuphaneAdi,char etiketDizisi[][100],int etiketSayaci[])
{
    FILE *dosya;
    DIR *kutuphane = opendir(kutuphaneAdi);
    char yol[100];
    char *yazi;
    char *aramaPtrBas;
    char *aramaPtrSon;
    char *sonParantezPtr;
    int kontrol=0;
    char *etiket = NULL;
    int boyut;
    if(kutuphane == NULL)
        return ;
    struct dirent* direntSeysi;
    direntSeysi = readdir(kutuphane);
    if(direntSeysi == NULL)
    {
        printf("Herhangi bir klasör açýlamadý\n");
        return;
    }
    while((direntSeysi=readdir(kutuphane))!=NULL)
    {
  
        if(strcmp(direntSeysi->d_name,".") !=0 && strcmp(direntSeysi->d_name,"..")!=0 && strcmp(direntSeysi->d_name,"...") !=0 && strcmp(direntSeysi->d_name,"output.txt") !=0 )
        {

            if(strstr(direntSeysi->d_name,".txt")!=0)
            {
                char acilacakDosyaYolu[100];
                strcpy(acilacakDosyaYolu,kutuphaneAdi);
                strcat(acilacakDosyaYolu,"/");
                strcat(acilacakDosyaYolu,direntSeysi->d_name);
                dosya = fopen(acilacakDosyaYolu,"r");
                if(dosya == NULL)
                {
                    printf("Dosya aï¿½?lamad?!\n");
                    fclose(dosya);
                    return;
                }
                else
                {
                    fseek(dosya,0,SEEK_END);
                    boyut = ftell(dosya);
                    if(boyut == 0)
                        {
                            continue;
                        }
                    else
                        fseek(dosya,0,SEEK_SET);
                    
                }
                yazi = (char*)calloc(boyut,1);
                if(yazi==NULL)
                    return; 

                fread(yazi,1,boyut,dosya);
                //printf(yazi);
                //printf("\n");
                aramaPtrBas = strstr(yazi,"[[");
                while(aramaPtrBas!=NULL && sonParantezPtr!= NULL)
                {
                    kontrol = 0;
                    aramaPtrSon=strstr(aramaPtrBas,"]]");
                    sonParantezPtr = strchr(aramaPtrBas,']');
                    
                        while(sonParantezPtr!=aramaPtrSon)
                        {
                            aramaPtrBas = strstr(aramaPtrBas+2,"[[");
                            if(aramaPtrBas==NULL)
                                break;
                            sonParantezPtr=(strchr(aramaPtrBas,']'));
                        }  
                    
                    if(aramaPtrSon && aramaPtrBas!=NULL)
                    {
                        aramaPtrBas = aramaPtrBas + 2;
                        etiket = (char*)malloc(aramaPtrSon-aramaPtrBas+1);
                        memcpy(etiket,aramaPtrBas,aramaPtrSon - aramaPtrBas);
                        etiket[aramaPtrSon-aramaPtrBas]='\0';
                        for(int i = 0;i<etiketSayaci[0]+1;i++)
                        {
                            if(strcmp(etiketDizisi[i],etiket)==0)
                            {
                                etiketSayaci[i+1]++;
                                kontrol = 1 ;
                                break;
                            }
                        }
                        if(kontrol!=1)
                        {
                            strcpy(etiketDizisi[etiketSayaci[0]],etiket);
                            etiketSayaci[etiketSayaci[0]+1]++;
                            etiketSayaci[0]++;
                            free(etiket);
                        }
                        aramaPtrBas = strstr(aramaPtrBas+2,"[[");
                    }
                    else
                        break;

                }
                fclose(dosya);
            }
            
            strcpy(yol,kutuphaneAdi);
            strcat(yol,"/");
            strcat(yol,direntSeysi->d_name);
            etiketTarama(yol,etiketDizisi,etiketSayaci);
        }
    }
    closedir(kutuphane);
}
void outputIlk(char etiketDizisi[][100],int etiketSayaci[],int yetimEtiketSayaci[])
{
    FILE *dosya = fopen("output.txt","w");
    fprintf(dosya,"Etiket Listesi -   Tekrar Sayýsý \n");
    for(int i = 0; i < etiketSayaci[0] ; i++)
    {
        fprintf(dosya,"%-30s %d\n",etiketDizisi[i],etiketSayaci[i+1]);
    }
    fprintf(dosya,"\nYetim Etkiketler \n");
    for(int i = 0; i < etiketSayaci[0];i++)
    {
        if(yetimEtiketSayaci[i]==0)
        {
            fprintf(dosya,"%s\n",etiketDizisi[i]);
        }
    }
    fclose(dosya);
}
void outputSon(char etiketDizisi[][100],int etiketSayaci[],int yetimEtiketSayaci[])
{
    FILE *dosya = fopen("output.txt","a");
    fprintf(dosya,"\n\n");
    fprintf(dosya,"Güncel Liste\n");
    fprintf(dosya,"Etiket Listesi -   Tekrar Sayýsý \n");
    for(int i = 0; i < etiketSayaci[0] ; i++)
    {
        fprintf(dosya,"%-30s %d\n",etiketDizisi[i],etiketSayaci[i+1]);
    }
    fprintf(dosya,"\nYetim Etkiketler \n");
    for(int i = 0; i < etiketSayaci[0];i++)
    {
        if(yetimEtiketSayaci[i+1]==0)
        {
            fprintf(dosya,"%s\n",etiketDizisi[i]);
        }
    }
    fclose(dosya);
}
void yetimEtiketTespiti(char *kutuphaneAdi,char etiketDizisi[][100],int etiketSayaci[],int yetimEtiketSayaci[])
{
    FILE *dosya;
    DIR *kutuphane = opendir(kutuphaneAdi);
    char yol[100];
    char *yazi;
    char *aramaPtrBas;
    char *aramaPtrSon;
    char *sonParantezPtr;
    int kontrol=0;
    char *etiket = NULL;
    int boyut;
    if(kutuphane == NULL)
        return ;
    struct dirent* direntSeysi;
    direntSeysi = readdir(kutuphane);
    if(direntSeysi == NULL)
    {
        printf("Herhangi bir klasï¿½r aï¿½ï¿½lamad?\n");
        return;
    }
    
    while((direntSeysi=readdir(kutuphane))!=NULL)
    {
  
        if(strcmp(direntSeysi->d_name,".") !=0 && strcmp(direntSeysi->d_name,"..")!=0 && strcmp(direntSeysi->d_name,"...") !=0 && strcmp(direntSeysi->d_name,"output.txt") !=0 )
        {

            if(strstr(direntSeysi->d_name,".txt")!=0)
            {
                
                for(int i = 0; i < etiketSayaci[0];i++)
                {
                    char temp[100];
                    strcpy(temp,etiketDizisi[i]);
                    strcat(temp,".txt");
                    if(strcmp(direntSeysi->d_name,temp)==0)
                    {
                        yetimEtiketSayaci[i]++;
                    }
                }
            }
            strcpy(yol,kutuphaneAdi);
            strcat(yol,"/");
            strcat(yol,direntSeysi->d_name);
            yetimEtiketTespiti(yol,etiketDizisi,etiketSayaci,yetimEtiketSayaci);
        }
    }
    closedir(kutuphane);
}
void dosyaTespiti(char *kutuphaneAdi,char klasorDizisi[][100],int klasorSayac[])
{
    FILE *dosya;
    DIR *kutuphane = opendir(kutuphaneAdi);
    char yol[100];
    char *yazi;
    char *aramaPtrBas;
    char *aramaPtrSon;
    char *sonParantezPtr;
    int kontrol=0;
    char *etiket = NULL;
    int boyut;
    if(kutuphane == NULL)
        return ;
    struct dirent* direntSeysi;
    direntSeysi = readdir(kutuphane);
    if(direntSeysi == NULL)
    {
        printf("Herhangi bir klasï¿½r aï¿½ï¿½lamad?\n");
        return;
    }
    
    while((direntSeysi=readdir(kutuphane))!=NULL)
    {
  
        if(strcmp(direntSeysi->d_name,".") !=0 && strcmp(direntSeysi->d_name,"..")!=0 && strcmp(direntSeysi->d_name,"...") !=0 && strcmp(direntSeysi->d_name,"output.txt") !=0 )
        {            
            if((strstr(direntSeysi->d_name,".")==0)&&direntSeysi->d_namlen>4)
            {
                strcpy(klasorDizisi[klasorSayac[0]],direntSeysi->d_name);
                klasorSayac[0]++;
            }
            strcpy(yol,kutuphaneAdi);
            strcat(yol,"/");
            strcat(yol,direntSeysi->d_name);
            dosyaTespiti(yol,klasorDizisi,klasorSayac);
        }
    }
    closedir(kutuphane);
}
void yeniDosyaAcma(char *kutuphaneAdi,char klasorDizisi[][100],char yetimEtiketDizisi[][100],int klasorSayac[],int ekleSecim1,int ekleSecim2)
{
    FILE *dosya;
    DIR *kutuphane = opendir(kutuphaneAdi);
    char yol[100];
    char acilacakKlasor[100];
    char *yazi;
    char yeniDosyaAdi[100];
    strcpy(acilacakKlasor,klasorDizisi[ekleSecim2]);
    strcpy(yeniDosyaAdi,yetimEtiketDizisi[ekleSecim1]);
    strcat(yeniDosyaAdi,".txt");
    char *aramaPtrBas;
    char *aramaPtrSon;
    char *sonParantezPtr;
    int kontrol=0;
    char *etiket = NULL;
    int boyut;
    if(kutuphane == NULL)
        return ;
    struct dirent* direntSeysi;
    direntSeysi = readdir(kutuphane);
    if(direntSeysi == NULL)
    {
        printf("Herhangi bir klasï¿½r aï¿½ï¿½lamad?\n");
        return;
    }
    
    while((direntSeysi=readdir(kutuphane))!=NULL)
    {
  
        if(strcmp(direntSeysi->d_name,".") !=0 && strcmp(direntSeysi->d_name,"..")!=0 && strcmp(direntSeysi->d_name,"...") !=0 && strcmp(direntSeysi->d_name,"output.txt") !=0 )
        {            
            if(strcmp(direntSeysi->d_name,acilacakKlasor)==0)
            {
                strcpy(yol,kutuphaneAdi);
                strcat(yol,"/");
                strcat(yol,acilacakKlasor);
                strcat(yol,"/");
                strcat(yol,yeniDosyaAdi);
                dosya = fopen(yol,"w");
                fprintf(dosya,"Dersin Kodu: BLM%d\n",klasorSayac[1]);
                klasorSayac[1]++;
                fprintf(dosya,"Dersin Adý : [[%s]]\n\n",yetimEtiketDizisi[ekleSecim1]);
                fprintf(dosya,"Dersin Ýçeriði :\n");
                fclose(dosya);
                break;
            }
            strcpy(yol,kutuphaneAdi);
            strcat(yol,"/");
            strcat(yol,direntSeysi->d_name);
            yeniDosyaAcma(yol,klasorDizisi,yetimEtiketDizisi,klasorSayac,ekleSecim1,ekleSecim2);
        }
    }
    closedir(kutuphane);
}
int main()
{
    
    FILE *dosya;
    setlocale(LC_ALL, "Turkish");
    int klasorSayac[2]={0,200};
    char aranacakKelime[100];
    char etiketDizisi[100][100];
    int etiketSayaci[100];
    int yetimEtiketSayaci[100];
    char degistikenSonrakiEtiket[100];
    char klasorDizisi[100][100];
    for(int i = 0 ; i < 100 ; i++)
    {
        etiketSayaci[i]=0;
        yetimEtiketSayaci[i]=0;
    }
    dosya = fopen("output.txt","w");
    fclose(dosya);
    etiketTarama(".",etiketDizisi,etiketSayaci);
    yetimEtiketTespiti(".",etiketDizisi,etiketSayaci,yetimEtiketSayaci);
    outputIlk(etiketDizisi,etiketSayaci,yetimEtiketSayaci);
    dosyaTespiti(".",klasorDizisi,klasorSayac);
    const int klasorSayisi = klasorSayac[0];
    bas:
    etiketTarama(".",etiketDizisi,etiketSayaci);
    yetimEtiketTespiti(".",etiketDizisi,etiketSayaci,yetimEtiketSayaci);
    printf("Yapýlacak iþlemi seçiniz : \n0-)Çýkýþ\n1-)Etiket veya kelime arama\n2-)Etiket deðiþtirme\n3-)Yetim etiket için txt oluþturma\n");
    int secim;
    scanf("%d",&secim);
    switch (secim)
    {
    case 0:
        for(int i = 0 ; i < 100 ; i++)
        {
            etiketSayaci[i]=0;
            yetimEtiketSayaci[i]=0;
        }
        etiketTarama(".",etiketDizisi,etiketSayaci);
        yetimEtiketTespiti(".",etiketDizisi,etiketSayaci,yetimEtiketSayaci);
        outputSon(etiketDizisi,etiketSayaci,yetimEtiketSayaci);
        break;
    case 1:
        printf("Etiket mi Kelime mi aramak istiyorsunuz :\n1-)Kelime\n2-)Etiket\n");
        int aramaSecim;
        scanf("%d",&aramaSecim);
        if(aramaSecim==1)
        {
            printf("Aranacak Kelimeyi giriniz :");
            fflush(stdin);
            fgets(aranacakKelime,100,stdin);
            aranacakKelime[strlen(aranacakKelime)-1]='\0';
            recursiveDosyaAramaVeAcma(".",aranacakKelime,etiketDizisi);
            for(int i = 0 ; i < 100 ; i++)
            {
                etiketSayaci[i]=0;
                yetimEtiketSayaci[i]=0;
            }
            etiketTarama(".",etiketDizisi,etiketSayaci);
            yetimEtiketTespiti(".",etiketDizisi,etiketSayaci,yetimEtiketSayaci);
            goto bas;
        }
        else if(aramaSecim==2)
        {
            int etiketAramaSecim;
            for(int i = 0;i<etiketSayaci[0];i++)
            {
                printf("%-3d-)%s\n",i,etiketDizisi[i]);
            }
            fflush(stdin);
            scanf("%d",&etiketAramaSecim);
            for(int i = 0 ; i < 100 ; i++)
            {
                etiketSayaci[i]=0;
                yetimEtiketSayaci[i]=0;
            }
            etiketArama(".",etiketDizisi,etiketAramaSecim);
        }
        goto bas;
        break;
    case 2:
        cs2:
        printf("Hangi etiketi deðiþtirmek istiyorsunuz ?(Numaralardan Seçiniz)\n");
        for(int i = 0;i<etiketSayaci[0];i++)
        {
            printf("%-3d-)%s\n",i,etiketDizisi[i]);
        }
        int degistirmeSecim;
        int eklemeSecim2;
        scanf("%d",&degistirmeSecim);
        if(degistirmeSecim<0 || degistirmeSecim > etiketSayaci[0])
            goto cs2;
        strcpy(degistikenSonrakiEtiket,etiketDizisi[degistirmeSecim]);
        fflush(stdin);
        printf("Ne ile deðiþtirmek istediðinizi giriniz:\n");
        fgets(degistikenSonrakiEtiket,100,stdin);
        degistikenSonrakiEtiket[strlen(degistikenSonrakiEtiket)-1]='\0';
        etiketDegistirme(".",etiketDizisi,degistikenSonrakiEtiket,degistirmeSecim);
        dosyaDegistirme(".",etiketDizisi,degistikenSonrakiEtiket,degistirmeSecim);
        for(int i = 0 ; i < 100 ; i++)
        {
            etiketSayaci[i]=0;
            yetimEtiketSayaci[i]=0;
        }
        etiketTarama(".",etiketDizisi,etiketSayaci);
        yetimEtiketTespiti(".",etiketDizisi,etiketSayaci,yetimEtiketSayaci);
        goto bas;
        break;
    case 3:
        printf("Hangi yetim etiket için txt dosyasý oluþturmak istersiniz ?\n");
        int j = 0;
        int eklemeSecim1;
        char yetimEtiketDizisi[100][100];
        int f = 0;
        for(int i = 0;i<etiketSayaci[0];i++)
        {
            if(yetimEtiketSayaci[i]==0)
            {
                strcpy(yetimEtiketDizisi[f],etiketDizisi[i]);
                printf("%-3d-)%s\n",f,yetimEtiketDizisi[f]);
                f++;
            }
        }
        scanf("%d",&eklemeSecim1);
        printf("Dosya hangi klasöre açýlsýn istersiniz?\n");
        for(int i=0;i<klasorSayisi;i++)
        {
            printf("%-3d-)%s\n",i,klasorDizisi[i]);
        }
        scanf("%d",&eklemeSecim2);
        yeniDosyaAcma(".",klasorDizisi,yetimEtiketDizisi,klasorSayac,eklemeSecim1,eklemeSecim2);
        for(int i = 0 ; i < 100 ; i++)
        {
            etiketSayaci[i]=0;
            yetimEtiketSayaci[i]=0;
        }
        etiketTarama(".",etiketDizisi,etiketSayaci);
        yetimEtiketTespiti(".",etiketDizisi,etiketSayaci,yetimEtiketSayaci);

        goto bas;
        break;
    default:
        printf("Lütfen geçerli bir seçenek giriniz \n");
        goto bas;
        break;
    }
    return 0;
    

}