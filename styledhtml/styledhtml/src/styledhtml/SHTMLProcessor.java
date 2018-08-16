package styledhtml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SHTMLProcessor {
	
	String blocks;
	String lineBlocks[];
	int n;
	int bufferN;
	String buffer[]=new String[999999];
	
	SHTMLProcessor(){
	}
	SHTMLProcessor(String blocks){
		this.blocks=blocks;
		stringToStringArray();
	}	
	String[] stringToStringArray(){
		StringTokenizer linesTokens = new StringTokenizer(blocks,"\n");
		bufferN=0;
		while(linesTokens.hasMoreTokens()){
			buffer[bufferN++]=linesTokens.nextToken();
		}
		return buffer;
	}
	
	void readBlocks(){
		int i=0;
		for(i=0;i<bufferN;++i){
			System.out.print(buffer[i]);
		}
		System.out.println("");
	}
	
	void lineProcessor(){
		int i=0;
		int oTag=0,eTag=0,tag=0;
		int startIndex = 0,endIndex = 0;
		boolean first=false;
		for(int t=0;t<bufferN;++t){
			for(i=t;i<bufferN;++i){
				//System.out.println("Iteration  : "+i);
	
				if(buffer[i].contains("{")){
					if(first==false){
						startIndex=i;
						first=true;
					}
					oTag++;
				}else if(buffer[i].contains("}")){
					eTag++;
					endIndex=i;
				}
				if(oTag==eTag){
					oTag=eTag=0;
					first=false;
					//System.out.println("Start Index  : "+startIndex+"\n End Index  : "+endIndex);
					processor(startIndex,endIndex);
				}
			}
		}
	}
	
	void processor(int startIndex,int endIndex){
		if(buffer[startIndex].contains("<") || buffer[startIndex].contains("</")){
			return;
		}
		//System.out.println("");
		String s=buffer[startIndex];
		StringTokenizer tokens = new StringTokenizer(s," ");
		String tagName=tokens.nextToken();
		//String startTagName="<"+tagName+">";
		char ch[]=s.toCharArray();
		int i=0;
		String asTag="";
		int n=s.length();
		boolean d=false;
		while(i<n && ch[i]!='{'){
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
					//System.out.println("In if tag i");
					aTag+="<";
					d=true;
					//++i;
				}else if(ch[i]=='\t' && d==false){
					//System.out.println("The else tag i");
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
			//System.out.println("in else");
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
		//System.out.println("A Tag is  : "+aTag);
		String em="";
		if(d==false){
			aTag="<"+aTag+">";
		}else{
			aTag=aTag+">";
		}
		tagName=tagName.replaceAll("\\{","");
		//tagName+="\n";
		tagName=tagName.replaceAll("[\\t\\n\\r]","");
		//System.out.println("Tag Name  : "+tagName+"ssss");
		String eTagName="</"+tagName+">";
		if(eTagName.contains("\n")){
			System.out.println("End Tag is  : "+eTagName);
		}
		String reTag="";
		char aTagCh[]=aTag.toCharArray();
		n=aTagCh.length;
		i=0;
		if(aTagCh[0]=='\t'){
			while(i<n){
				if(aTagCh[i]=='\t'){
					reTag+=aTagCh[i];
				}
				i++;
			}
		}else if(aTagCh[0]==' '){
			while(i<n){
				if(aTagCh[i]==' '){
					reTag+=aTagCh[i];
				}
				i++;
			}
		}
		eTagName=reTag+eTagName;
		buffer[startIndex]=aTag;
		buffer[endIndex]=eTagName;
		i=0;
		for(i=startIndex+1;i<endIndex;++i){
			buffer[i]=buffer[i].replaceAll("[\\n\\r]","");
		}
		/*
		for(int t=0;t<bufferN;++t){
			System.out.print(buffer[t]);
		}
		*/
	}
	
	void printBuffer(){
		int i=0;
		for(i=0;i<bufferN;++i){
			System.out.println(buffer[i]);
		}
	}
	void writeBufferToFile(String name){
		StringTokenizer fileExtension = new StringTokenizer(name,".");
		try{
			fileExtension.nextToken();
			fileExtension.nextToken();
		}catch(Exception e){
			name+=".html";
		}
		File file = new File(name);
		int i;
		String s="";
		for(i=0;i<bufferN;++i){
			s+=buffer[i]+"\r";
		}
		try {

			FileOutputStream fos = new FileOutputStream(file);
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
			for(i=0;i<bufferN;++i){
				bufferedWriter.write(buffer[i].toCharArray());
				bufferedWriter.newLine();
			}
			//bufferedWriter.write(s.toCharArray());
			/*
			fos.write(s.getBytes());
			fos.flush();
			fos.close();
			*/
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
