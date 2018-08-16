package styledhtml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) {
		Scanner sr = new Scanner(System.in);
		/*
		
		//String s="          h1 class=\"hi div\"{";
		String s=sr.nextLine();
		StringTokenizer tokens = new StringTokenizer(s," ");
		System.out.println("Tag Name is  : ");
		String tagName=tokens.nextToken();
		//String startTagName="<"+tagName+">";
		char ch[]=s.toCharArray();
		int i=0;
		String asTag="";
		int n=s.length();
		boolean d=false;
		while(ch[i]!='{'){
			asTag+=ch[i++];
		}
		ch=asTag.toCharArray();
		//System.out.println("ch string is  : "+String.valueOf(ch));
		String aTag="";
		i=0;
		n=ch.length;
		if(ch[0]==' ' || ch[0]=='\t'){
			while(i<n){
				if(ch[i]!='\t' && ch[i]!=' ' && d==false){
					System.out.println("In if tag i");
					aTag+="<";
					d=true;
					//++i;
				}else if(ch[i]=='\t' && d==false){
					System.out.println("The else tag i");
					while(ch[i]=='\t'){
						i++;
						aTag+="\t";
					}
					aTag+="<";
					d=true;
				}
					aTag+=ch[i++];
				
			}
		}else{
			System.out.println("in else");
			while(i<n){
				aTag+=ch[i];
				i++;
			}
		}
		ch=tagName.toCharArray();
		n=ch.length;
		i=0;
		tagName="";
		while(i<n){
			if(ch[i]=='\t'){
				i++;
			}else{
				tagName+=ch[i++];
			}		
		}
		System.out.println("A Tag is  : "+aTag);
		String em="";
		if(d==false){
			aTag="<"+aTag+">";
		}else{
			aTag=aTag+">";
		}
		tagName=tagName.replaceAll("\\{","");
		String eTagName="</"+tagName+">";
		//eTagName.replace("{","");
		System.out.println(aTag+" e "+eTagName);
		*/
		
		File file=new File("D:\\AllWorks\\Works\\styledhtml\\styledhtml\\src\\styledhtml\\fileName.shtmls");
		try {
			FileInputStream fis = new FileInputStream(file);
			String text="";
			int content;
			while((content=fis.read())!=-1){
				text+=(char)content;
			}
			//System.out.println(text);
			SHTMLProcessor processor = new SHTMLProcessor(text);
			//processor.readBlocks();
			processor.lineProcessor();
			processor.printBuffer();
			processor.writeBufferToFile("home");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

}
