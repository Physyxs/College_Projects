/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package filecopy;

/**
 *
 * @author brand
 */
import java.io.*;
import java.util.*;

public class FileCopy
{
	public static void main(String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(System.in);
		
		System.out.println("Give an Input File Name.");
		String inName = in.next();
		
		System.out.println("Give an Output File Name.");
		String outName = in.next();
		
		File inFile = new File(inName);
		Scanner scan = new Scanner(inFile);
		PrintWriter out = new PrintWriter(outName);
		
		while(scan.hasNextLine()){
		    out.println(scan.nextLine());
		}
		
		scan.close();
		out.close();
	}
}
