/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 15:08:20
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
/**
 *
 */
package dao.exceptions;

import java.sql.SQLException;

/**
 * @author 31010-79-11
 *
 */
public class DAOSQLException extends SQLException {

	private static final long serialVersionUID = -6111973666438757776L;

	private int codeErreur;
	private String messageErreur;

	public DAOSQLException() {
		System.out.println("DBSQLException : Exception de type DBSQLException ");
	}

	public DAOSQLException(String message) {
		this(message, 0);
	}

	public DAOSQLException(String message, Throwable cause) {
		super("DBException" + message, cause);
		codeErreur = DAOExceptionMessages.EXCEPTIONCHAINEE;
	}

	public DAOSQLException(Throwable cause) {
		this(null, cause);
	}

	public DAOSQLException(String message, int codeErreur) {
		super(message);
		System.out.println("Code d'erreur = "+codeErreur);
		switch(codeErreur)
		{
		case 1017 :
			this.codeErreur = DAOExceptionMessages.CONNEXIONIMPOSSIBLE;
			messageErreur = DAOExceptionMessages.message[this.codeErreur];
			System.out.println(messageErreur + "  :  "+codeErreur);
			break;

		case DAOExceptionMessages.PILOTENONTROUVE :
			this.codeErreur = DAOExceptionMessages.PILOTENONTROUVE;
			messageErreur = DAOExceptionMessages.message[this.codeErreur];
			System.out.println(messageErreur + "  :  "+codeErreur);
			break;

			// problème de connexion
		case 17002 :
			this.codeErreur = DAOExceptionMessages.CONNEXIONIMPOSSIBLE;
			messageErreur = DAOExceptionMessages.message[this.codeErreur];
			System.out.println(messageErreur + "  :  "+codeErreur);
			break;

			//ORA-17008 Closed Connection
		case 17008 :
			this.codeErreur = DAOExceptionMessages.CONNEXIONIMPOSSIBLE;
			messageErreur = DAOExceptionMessages.message[this.codeErreur];
			System.out.println(messageErreur + "  :  "+codeErreur);
			break;

		default:
			this.codeErreur = DAOExceptionMessages.AUTREEXCEPTION;
			messageErreur = DAOExceptionMessages.message[this.codeErreur];
			System.out.println(messageErreur + "  :  "+codeErreur);
			break;
		}
	}

	public int getCode() {
		return codeErreur;
	}

	public String getMessageErreur() {
		return messageErreur;
	}
}

/*
 ORA-17001 Internal Error
ORA-17002 Io exception
ORA-17003 Invalid column index
ORA-17004 Invalid column type
ORA-17005 Unsupported column type
ORA-17006 Invalid column name
ORA-17007 Invalid dynamic column
ORA-17008 Closed Connection
ORA-17009 Closed Statement
ORA-17010 Closed Resultset
ORA-17011 Exhausted Resultset
ORA-17012 Parameter Type Conflict
ORA-17014 ResultSet.next was not called
ORA-17015 Statement was cancelled
ORA-17016 Statement timed out
ORA-17017 Cursor already initialized
ORA-17018 Invalid cursor
ORA-17019 Can only describe a query
ORA-17020 Invalid row prefetch
ORA-17021 Missing defines
ORA-17022 Missing defines at index
ORA-17023 Unsupported feature
ORA-17024 No data read
ORA-17025 Error in defines.isNull ()
ORA-17026 Numeric Overflow
ORA-17027 Stream has already been closed
ORA-17028 Can not do new defines until the current ResultSet is closed
ORA-17029 setReadOnly: Read-only connections not supported
ORA-17030 READ_COMMITTED and SERIALIZABLE are the only valid transaction levels
ORA-17031 setAutoClose: Only support auto close mode on
ORA-17032 cannot set row prefetch to zero
ORA-17033 Malformed SQL92 string at position
ORA-17034 Non supported SQL92 token at position
ORA-17035 Character Set Not Supported !!
ORA-17036 exception in OracleNumber
ORA-17037 Fail to convert between UTF8 and UCS2
ORA-17038 Byte array not long enough
ORA-17039 Char array not long enough
ORA-17040 Sub Protocol must be specified in connection URL
ORA-17041 Missing IN or OUT parameter at index:
ORA-17042 Invalid Batch Value
ORA-17043 Invalid stream maximum size
ORA-17044 Internal error: Data array not allocated
ORA-17045 Internal error: Attempt to access bind values beyond the batch value
ORA-17046 Internal error: Invalid index for data access
ORA-17047 Error in Type Descriptor parse
ORA-17048 Undefined type
ORA-17049 Inconsistent java and sql object types
ORA-17050 no such element in vector
ORA-17051 This API cannot be be used for non-UDT types
ORA-17052 This ref is not valid
ORA-17053 The size is not valid
ORA-17054 The LOB locator is not valid
ORA-17055 Invalid character encountered in
ORA-17056 Non supported character set (add orai18n.jar in your classpath environment variable)
ORA-17057 Closed LOB
ORA-17058 Internal error: Invalid NLS Conversion ratio
ORA-17059 Fail to convert to internal representation
ORA-17060 Fail to construct descriptor
ORA-17061 Missing descriptor
ORA-17062 Ref cursor is invalid
ORA-17063 Not in a transaction
ORA-17064 Invalid Sytnax or Database name is null
ORA-17065 Conversion class is null
ORA-17066 Access layer specific implementation needed
ORA-17067 Invalid Oracle URL specified
ORA-17068 Invalid argument(s) in call
ORA-17069 Use explicit XA call
ORA-17070 Data size bigger than max size for this type
ORA-17071 Exceeded maximum VARRAY limit
ORA-17072 Inserted value too large for column
ORA-17073 Logical handle no longer valid
ORA-17074 invalid name pattern
ORA-17075 Invalid operation for forward only resultset
ORA-17076 Invalid operation for read only resultset
ORA-17077 Fail to set REF value
ORA-17078 Cannot do the operation as connections are already opened
ORA-17079 User credentials doesn't match the existing ones
ORA-17080 invalid batch command
ORA-17081 error occurred during batching
ORA-17082 No current row
ORA-17083 Not on the insert row
ORA-17084 Called on the insert row
ORA-17085 Value conflicts occurs
ORA-17086 Undefined column value on the insert row
ORA-17087 Ignored performance hint: setFetchDirection()
ORA-17088 Unsupported syntax for requested resultset type and concurrency level
ORA-17089 internal error
ORA-17090 operation not allowed
ORA-17091 Unable to create resultset at the requested type and/or concurrency level
ORA-17092 JDBC statements cannot be created or executed at end of call processing
ORA-17093 OCI operation returned OCI_SUCCESS_WITH_INFO
ORA-17094 Object type version mismatched
ORA-17095 Statement Cache size has not been set
ORA-17096 Statement Caching cannot be enabled for this logical connection
ORA-17097 Invalid PL/SQL Index Table element type
ORA-17098 Invalid empty lob operation
ORA-17099 Invalid PL/SQL Index Table array length
ORA-17100 Invalid database Java Object
ORA-17101 Invalid properties in OCI Connection Pool Object
ORA-17102 Bfile is read only
ORA-17103 invalid connection type to return via getConnection. Use getJavaSqlConnection instead
ORA-17104 SQL statement to execute cannot be empty or null
ORA-17105 connection session time zone was not set
ORA-17106 invalid combination of connections specified
ORA-17107 invalid proxy type specified
ORA-17108 No max length specified in defineColumnType
ORA-17109 standard Java character encoding not found
ORA-17110 execution completed with warning
ORA-17111 Invalid connection cache TTL timeout specified
ORA-17112 Invalid thread interval specified
ORA-17113 Thread interval value is more than the cache timeout value
ORA-17114 could not use local transaction commit in a global transaction
ORA-17115 could not use local transaction rollback in a global transaction
ORA-17116 could not turn on auto-commit in an active global transaction
ORA-17117 could not set savepoint in an active global transaction
ORA-17118 could not obtain ID for a named Savepoint
ORA-17119 could not obtain name for an un-named Savepoint
ORA-17120 could not set a Savepoint with auto-commit on
ORA-17121 could not rollback to a Savepoint with auto-commit on
ORA-17122 could not rollback to a local txn Savepoint in a global transaction
ORA-17123 Invalid statement cache size specified
ORA-17124 Invalid connection cache Inactivity timeout specified
ORA-17125 Improper statement type returned by explicit cache
ORA-17126 Fixed Wait timeout elapsed
ORA-17127 Invalid Fixed Wait timeout specified
 */
