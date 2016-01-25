package services.core;

import com.google.gson.JsonObject;

import app.utils.ServiceUtils;
import services.objects.RequestObject;
import services.objects.ResponseObject;

public abstract class AbstractService {
	
	protected ResponseObject responseObject;

	public AbstractService(){
		this.responseObject = new ResponseObject();
	}

	// GET-SET ////////////////////////////////////////////////////////////////
	
    public ResponseObject getResponseObject() { return responseObject; }       
    
    public void setResponseObject(ResponseObject responseObject) { this.responseObject = responseObject; }       
    
    // PUBLIC METHODS /////////////////////////////////////////////////////////
    
	/**
     * Permet de :
     *  - Verifier si tous les parametres obligatoire sont presents
     *  - Verifier la validite des donnees envoyees
     * @param RequestObject requestObject : contient le flux json envoye par le client
     * @param requiredData : liste des champs requis
     * @return boolean : Vrai si tous les champs requis sont presents
     */
	protected boolean validate(final RequestObject requestObject, final String[] requiredData) {
		final JsonObject data = requestObject.getData();

    	// Verification des champs requis
    	for(String champ : requiredData){
    		if(data.get(champ) == null){
    			ServiceUtils.logger.error("Service " +requestObject.getNomService()+ ": champ " +champ+ " requis manquant.");
    			return false;
    		}
    	}
		return true;
	}
    

    abstract public ResponseObject execute(final RequestObject requestObject);
    
    

    
	
	
	
	
	
}
