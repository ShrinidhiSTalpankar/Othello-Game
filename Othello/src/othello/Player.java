/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

/**
 *
 * @author Shrinidhi
 */
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
 
public class Player
{
    Scanner read;
    PrintWriter write;
     
            Player_list[] pl = new Player_list[20];
            int i, q=0;
           
           
             public static void main(String args[])throws IOException{
                Player p = new Player();
                
                
      
   }
      
     Player(){
          while(q<20)
            {
                pl[q]= new Player_list();
                q++;
            }
    
     
            try
            {
                read = new Scanner (new FileReader ("leader.txt"));
                 i=0;
                while (read.hasNext ())
                    {
             
                    pl[i].name=read.next();
                    pl[i].score=read.nextInt();
                    
                    i++;
                }
 
            }
 
            catch (FileNotFoundException fnfe)
            {
                System.out.println(fnfe+": FILE NOT FOUND!");
            }
            catch (InputMismatchException ime)
            {
                System.out.println(ime+": INVALID DATA!");
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
             
             
            int u, t=0;
            Player_list temp; 
            for (u = 0; u < i-1; ++u)
            {
 
                for(int v=0;v<i-u-1;v++)
                {
                    if(pl[v].score<pl[v+1].score)
                    {
                      temp=pl[v];
                      pl[v]=pl[v+1];
                      pl[v+1]=temp;
                    }
                }
 
            }
            Player_list[] tp = new Player_list[20];
            for(u=0;u<20;u++)
                tp[u]= new Player_list();
            int c=0;
       
            for(u=0;u<i;u++)
            {
                tp[c]=pl[u];
               
                for(int v=u+1;v<i;v++)
                {
                    if(pl[v].name.equals(tp[c].name))
                    {
                        del(pl,pl[v]);
                        i--;
                    }
                }
                c++;
            }
            pl=tp;
           
               String a="";  
            try{
             
            write = new PrintWriter("leader.txt");
 
                      
                 
                for (int z = 0; z < i; ++z)
                {
                 
                write.print(pl[z].name+" ");
                write.println (pl[z].score);
                a=a+pl[z].name+" "+pl[z].score+"\n";
                 
                }
                write.flush();
                        write.close ();
            }
 
            catch (FileNotFoundException fnfe)
            {
                System.out.println(fnfe+": FILE NOT FOUND!");
            }
                        catch (InputMismatchException ime)
            {
                System.out.println(ime+": INVALID DATA!");
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            
            LeaderBoard l = new LeaderBoard(pl,i);
            l.setVisible(true);
            
           }
     
     void del(Player_list[] p, Player_list s)
     {
         for(int k=0;k<i;k++)
         {
             if(p[k].name.equals(s.name) && p[k].score==s.score)
             {
                 System.out.println(p[k].name+" "+p[k].score);
                 for(int l=k;l<i-1;l++)
                 p[l]=p[l+1];
                 break;
             }
         }
     }
         
 
}
class Player_list{
    String name;
    int score;
}