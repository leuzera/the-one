/**
 * SlotTimeCheck.java is part of Dlife and DlifeComm.
 * 
 * Copyright 2012 SITI, Universidade Lusófona
 * 
 * Dlife and DlifeComm are free softwares: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Dlife and DlifeComm are distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Dlife and DlifeComm.  If not, see <http://www.gnu.org/licenses/>.
 *
 * 
 */

package core;

import routing.DecisionEngineRouter;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class SlotTimeCheck {
	
	
	private static int currentslot;
	private static ArrayList<Long> slotspecifications;
	private static int numberofslots;
	private static long currentslottime;
	private static long prevtime;
	private static List<DTNHost> hosts;
	public static long currentday; // CHANGED FROM: private static long currentday;
	public static int normalisation;
	private static long currentslotlength;
	
	public SlotTimeCheck(ArrayList<Long> slotsettings){
		currentslot=0;
//		System.out.println("hallo");
		slotspecifications=slotsettings;
		numberofslots=slotspecifications.size();
		currentday=1;
		if(slotspecifications.size()==0){
			System.out.print("No slotsettings found!!!");
			System.exit(0);
		}
		currentslottime=slotspecifications.get(0);
		prevtime=0;
		normalisation=0;
		currentslotlength=slotspecifications.get(0);
		for(int i=1;i<=numberofslots;i++){
			normalisation=normalisation+i;
		}
	}
	public static int getcurrentslot(){
		return currentslot;
	}
	public static int getnumberofslots(){
		return numberofslots;
	}
	

	
	public static void update(double time){
		long currentTime=((long)time)%86400;
	//	System.out.println(""+currentTime+"   "+currentslottime+"   "+prevtime);
		if((currentTime>=currentslottime||currentTime<prevtime)&currentTime>=0){
			hosts=SimScenario.getInstance().getHosts();
			ListIterator<DTNHost> iter = hosts.listIterator();
//			System.out.println("Dia: "+currentday+" Slot: "+currentslot);
			while(iter.hasNext()){
				DTNHost currenthost=iter.next();
				((DecisionEngineRouter)(currenthost.getRouter())).calcdeltaTandAD();
			}
			currentslot=(currentslot+1)%numberofslots;
			if(currentslot==0){
			currentday=currentday+1;	
			currentslotlength=slotspecifications.get(0);
			}
			else{
				currentslotlength=slotspecifications.get(currentslot)-slotspecifications.get(currentslot-1);
			}
		}
		currentslottime=slotspecifications.get(currentslot);
		prevtime=currentTime;
	}
	public static long getDay(){
		return currentday;
	}
	public static int getnormalisation(){
		return normalisation;
	}
	
	public static long getcurrentslotlength(){
		return currentslotlength;
	}
}
