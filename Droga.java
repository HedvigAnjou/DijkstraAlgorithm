import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class City{
City(){

    sasiedzi=new ArrayList<Integer>();
    odleglosci=new ArrayList<Integer>();

}   public Integer id;
    public String name;
    public ArrayList<Integer> sasiedzi;
    public ArrayList<Integer> odleglosci;

    public int odleglosc;
public int getOdleglosc(){

    return odleglosc;

}
}
public class Main {


public static City zwrocMiastoZNajmiejszaOdlegloscia(ArrayList<City> lista){

  //  lista.sort((City o1,City o2)-> o1.odleglosc.compareTo(o2.odleglosc));
    Collections.sort(lista, new Comparator<City>() {
        public int compare(City s1, City s2) {
            Integer o1=s1.getOdleglosc();
            Integer o2=s2.getOdleglosc();
            return o1.compareTo(o2);
        }
    });


    return lista.get(0);

}


public static void ogarnijSasiadow(City miasto,ArrayList<City> lista,ArrayList<City> listaNieOdwiedzonychMiast,ArrayList<City> listaOdwiedzonych){
    for(Integer a: miasto.sasiedzi){
        if(!listaOdwiedzonych.contains(a)) {
            for (City b : lista) {
                if (b.id == a) {

                    if (miasto.odleglosci.get(miasto.sasiedzi.indexOf(a)) + miasto.odleglosc < b.odleglosc) {
                        b.odleglosc = miasto.odleglosci.get(miasto.sasiedzi.indexOf(a)) + miasto.odleglosc;
                    listaNieOdwiedzonychMiast.add(b);

                    }
                }

            }
        }
        else{
            //bylismy juz w tym miescie.


        }




    }




}
    public static void main(String[] args){
    try{

        //drogi do odnalezienia
        ArrayList<String> zJakiegoMiasta=new ArrayList<String>();
        ArrayList<String> doJakiegoMiasta=new ArrayList<String>();


        ArrayList<City> listaMiast=new ArrayList<City>();

        BufferedReader bfreader = null;
        bfreader = new BufferedReader(new InputStreamReader(System.in));
        String numberOfTests_str="";
        String numberOfCities_str="";

        numberOfTests_str= bfreader.readLine();



        int numberOfTests=Integer.parseInt(numberOfTests_str);
        int numberOfCities=0;


        for(int t=0;t<numberOfTests;t++) {

            numberOfCities_str= bfreader.readLine();
         //   System.out.print("Miast jest"+numberOfCities_str);
            numberOfCities=Integer.parseInt(numberOfCities_str);
            for (int c = 0; c < numberOfCities; c++) {
                City noweMiasto = new City();
                noweMiasto.id=c+1;
                noweMiasto.name = bfreader.readLine();
                //System.out.print("Nazwa:"+noweMiasto.name);
                int ileSasiadow = Integer.parseInt(bfreader.readLine());
                for(int s=0;s<ileSasiadow;s++){
                    String miastoIOdleglosc=bfreader.readLine();
                    String miasta[]=miastoIOdleglosc.split("\\s");
                    noweMiasto.sasiedzi.add(Integer.parseInt(miasta[0]));
                    noweMiasto.odleglosci.add(Integer.parseInt(miasta[1]));

                }
                listaMiast.add(noweMiasto);
            }


            int ileDrogDoSprawdzenia=Integer.parseInt(bfreader.readLine());
            for(int s=0;s<ileDrogDoSprawdzenia;s++){
                String miastoIOdleglosc=bfreader.readLine();
                String miasta[]=miastoIOdleglosc.split("\\s");
                zJakiegoMiasta.add(miasta[0]);
                doJakiegoMiasta.add(miasta[1]);
            }


            int odleglosci[];

            for(int s=0;s<ileDrogDoSprawdzenia;s++){
                ArrayList<City> listaNieOdwiedzonychMiast=new ArrayList<City>();
                ArrayList<City> listaOdwiedzonych=new ArrayList<City>();

                for(City m: listaMiast){
                    if(m.name.equals(zJakiegoMiasta.get(s)))
                    {
                        m.odleglosc=0;
                        listaNieOdwiedzonychMiast.add(m);
                    }
                    else
                        m.odleglosc= 200000;
                   // listaNieOdwiedzonychMiast.add(m);
                }
//tu kod

                while(!listaNieOdwiedzonychMiast.isEmpty()){
                    City currentNode=zwrocMiastoZNajmiejszaOdlegloscia(listaNieOdwiedzonychMiast);
                   // System.out.println("Najkrotsze to ma byc: "+currentNode.odleglosc);
                    listaNieOdwiedzonychMiast.remove(currentNode);
                    listaOdwiedzonych.add(currentNode);
                    ogarnijSasiadow(currentNode,listaMiast,listaNieOdwiedzonychMiast,listaOdwiedzonych);

                }
                for(City m:listaMiast){
                    if(m.name.equals(doJakiegoMiasta.get(s)))
                        System.out.println(m.odleglosc);


                }
                //City koniec=
                //Integer droga=


            }











            }
        } catch (IOException e) {
            e.printStackTrace();
    }
        }
    }
