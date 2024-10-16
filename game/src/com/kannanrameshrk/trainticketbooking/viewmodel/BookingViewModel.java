package com.kannanrameshrk.trainticketbooking.viewmodel;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.kannanrameshrk.trainticketbooking.dto.Passanger;

class BookingViewModel {
	public BookingView bookingView;
	private List<Passanger> data=new ArrayList<>();
	private Queue<Passanger> wl=new LinkedList<>();
	private static int ticketCount=8;
	static char[][] chart= new char[ticketCount][5];
	private static int waitingList=2;
	
	
	public BookingViewModel(BookingView bookingView) {
		this.bookingView=bookingView;
	}


	public char[][] getChart() {
		return chart;
	}

	public void bookTicket(Passanger passanger) {
		char start=passanger.getStartPoint();
		char end=passanger.getEndPoint();
		int tickets=passanger.getTicketCount();
		
		if(checkAvailableTickets(start,end,tickets)) {
			System.out.println("Tickets is Available...");
			bookTicketsChart(start,end,tickets,passanger);
			passanger.setConfirm(true);
			data.add(passanger);
		}else {
			if(tickets<=waitingList) {
				for(int i=0;i<2;i++) {
						wl.add(passanger);
						waitingList--;
						System.out.print("W"+(i+1)+",");
				}
				System.out.println(" is allocated and pnr is "+passanger.getPnr());
			}else {
				System.out.println("sorry no tickets available....");
			}
		}
		
	}

	private void bookTicketsChart(char start, char end, int tickets, Passanger passanger) {
		Set<Integer> seat=new LinkedHashSet<>();
		
		for(int i=0;i<chart.length&& tickets!=0;i++) {
				boolean flag=true;
				for(int j=start-'A';j<end-'A';j++) {
					if(chart[i][j]=='*') {
						flag=false;
						break;
					}
				}
				if(flag) {
					tickets--;
					for(int j=start-'A';j<end-'A';j++) {
						chart[i][j]='*';
						seat.add(i+1);
					}
				}
		}
		passanger.getSeatNo().addAll(seat);
		System.out.println(seat+" is allocated.. pnr is "+ passanger.getPnr());
	}

	private boolean checkAvailableTickets(char start, char end, int tickets) {
		int count=0;
		for(int i=0;i<chart.length;i++) {
			boolean flag=true;
			for(int j=start-'A';j<end-'A';j++) {
				if(chart[i][j]=='*') {
					flag=false;
					break;
				}
			}
			if(flag)
			count++;
		}
		return count>=tickets;
	}

	public boolean checkPNR(int pnr) {
		for(Passanger p:data) {
			if(p.getPnr()==pnr) {
				return true;
			}
		}
		return false;
	}

	public void ticketCancel(int pnr, int noOfTickets) {
		List<Passanger> toBeMoved = new ArrayList<>();

        for (Passanger p : data) {
            if (p.getPnr() == pnr) {
                if (p.getTicketCount() < noOfTickets) {
                    System.out.println("Your ticket count is low, but cancel tickets is higher..err");
                } else {
                    List<Integer> seat = p.getSeatNo();
                    List<Integer> ds=new ArrayList<>();
                    
                    int k = 0;
                    int originalTicketCount = noOfTickets;

                    for (int i = 0; i < ticketCount && noOfTickets != 0; i++) {
                        for (int j = p.getStartPoint() - 'A'; j < p.getEndPoint() - 'A'; j++) {
                            chart[seat.get(k) - 1][j] = '\u0000';
                            ds.add(k);
                        }
                        noOfTickets--;
                        k++;
                    }
                   
                    p.setTicketCount(p.getTicketCount() - originalTicketCount);
                    for (Integer seatNumber : ds) {
                        seat.remove(seatNumber); // Remove the seat number from the list
                    }
                    int j = 1;

                    while (!wl.isEmpty()) {
                        Passanger waitingPassanger = wl.peek();
                        char start = waitingPassanger.getStartPoint();
                        char end = waitingPassanger.getEndPoint();
                        int tickets = waitingPassanger.getTicketCount();

                        if (checkAvailableTickets(start, end, tickets)) {
                            bookTicketsChart(start, end, tickets, waitingPassanger);
                            System.out.println("W" + j + " is moved to confirmed tickets..");
                            toBeMoved.add(wl.remove());
                            wl.remove();
                        } else {
                            break;
                        }
                        j++;
                    }
                }
                data.addAll(toBeMoved);
                break;
            }
        }
	}

}
