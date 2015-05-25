package org.ngo.think.dm.service;

public class RequestClosureException extends Exception
{

	private static final long serialVersionUID = 1990902114923390587L;

		public RequestClosureException()
		{
		}

		public RequestClosureException(String message)
		{
			super(message);
		}

		public RequestClosureException(Throwable cause)
		{
			super(cause);
		}

		public RequestClosureException(String message, Throwable cause)
		{
			super(message, cause);
		}

		public RequestClosureException(String message, Throwable cause, 
                                           boolean enableSuppression, boolean writableStackTrace)
		{
			super(message, cause, enableSuppression, writableStackTrace);
		}

}

