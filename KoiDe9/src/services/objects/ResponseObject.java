package services.objects;

import java.util.List;

import com.google.gson.Gson;

/**
 * @author ZZE
 *
 */
public class ResponseObject {
	
    private int 	returnCode;
    private String 	message;
    private List<?> response;

    public final static int RETURN_CODE_OK 		= 0;
    public final static int RETURN_CODE_WARNING = 4;
    public final static int RETURN_CODE_ERROR 	= 8;
    
    public ResponseObject(){}
    
    public ResponseObject(int returnCode, String message){
    	this.setResponseData(returnCode, message, null);
    }
    
    public ResponseObject(int returnCode, String message, List<?> data){
    	this.setResponseData(returnCode, message, data);
    }
    
    // GET SET ////////////////////////////////////////////////////////////////
    public int 			getReturnCode() 	{ return returnCode;	}
    public String 		getMessage()		{ return message; 		}   
    public List<?>		getResponseData() 	{ return response; 		}   

    public void setReturnCode(int returnCode) 	{ this.returnCode 	= returnCode; 	}   
    public void setMessage(String messages) 	{ this.message 		= messages; 	}   
	public void setResponseData(List<?> data) 	{ this.response 	= data;			} 
	
	///////////////////////////////////////////////////////////////////////////

	/**
	 * Permet de attribuer les elements de la reponse
	 * @param returnCode code de retour 0: OK, 4: Warning, 8: Error
	 * @param message : description 
	 * @param d
	 */
	public void setResponseData(final int returnCode, final String message, final List<?> data){
		this.setReturnCode(returnCode);
		this.setMessage(message);
		this.setResponseData(data);
    }


    /**
     * Transform l'objet ResponseObject en JSON
     * @return string JSON
     */
    public String getJsonReponse(){
    	final Gson gson = new Gson();
    	return gson.toJson(this);
    }
    
}
