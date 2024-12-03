package org.sight.kiosk.rfidUtils;//===========================================================================================
// 
//  Author          : Mildred L. Tosoc
// 
//  File            : PollingEvents.java
// 
//  Copyright (C)   : Advanced Card Systems Ltd.
// 
//  Description     : Container class for the event class & event generator class
// 
//  Date            : April 17, 2013
// 
//  Revision Trail : [Author] / [Date of modification] / [Details of Modifications done]
// 
//=========================================================================================


import org.sight.kiosk.enums.CARD_STATUS;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;


public class PollingEvent {

	public class CardPollingEventArg extends EventObject
	{
		
		public String  reader;
		public CARD_STATUS status;
		public byte[] atr;
		
		public CardPollingEventArg(Object sender) {
			super(sender);
			// TODO Auto-generated constructor stub
		}		
	}
	
	public interface CardPollingHandler
	{
		public void onCardFound(CardPollingEventArg event);
		public void onCardRemoved(CardPollingEventArg event);
	}
	
	private List<CardPollingHandler> _listener = new ArrayList<CardPollingHandler>();
	
	public List<CardPollingHandler> getListeners()
	{
		return this._listener;
	}
	
	public void setListener(List<CardPollingHandler> listener)
	{
		this._listener = listener;
	}
	
	public synchronized void addEventListener(CardPollingHandler listener)
	{
		this.getListeners().add(listener);
	}
	
	public synchronized void removeEventListener(CardPollingHandler listener)
	{
		this.getListeners().remove(listener);
	}
	
	public synchronized void cardFound()
	{
		CardPollingEventArg event = new CardPollingEventArg(this);
		Iterator<CardPollingHandler> listener = this.getListeners().iterator();
		
		while(listener.hasNext())
		{
			((CardPollingHandler) listener.next()).onCardFound(event);
		}
	}
	
	public synchronized void cardRemoved()
	{
		CardPollingEventArg event = new CardPollingEventArg(this);
		Iterator<CardPollingHandler> listener = this.getListeners().iterator();
		
		while(listener.hasNext())
		{
			((CardPollingHandler) listener.next()).onCardRemoved(event);
		}
	}
}
