package org.ngo.think.dm.service;

public class InsufficientDonationException extends Exception
{

    private static final long serialVersionUID = 1997753363232807009L;

		public InsufficientDonationException()
		{
		}

		public InsufficientDonationException(String message)
		{
			super(message);
		}

		public InsufficientDonationException(Throwable cause)
		{
			super(cause);
		}

		public InsufficientDonationException(String message, Throwable cause)
		{
			super(message, cause);
		}

		public InsufficientDonationException(String message, Throwable cause, 
                                           boolean enableSuppression, boolean writableStackTrace)
		{
			super(message, cause, enableSuppression, writableStackTrace);
		}

}

