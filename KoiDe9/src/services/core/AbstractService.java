package services.core;

import javax.servlet.ServletRequest;

import com.google.gson.JsonObject;

import app.utils.ServiceUtils;
import services.objects.RequestObject;
import services.objects.ResponseObject;

public abstract class AbstractService {
	
	protected ResponseObject responseObject;
	protected String[] requiredData;
	
	public AbstractService(){
		this.responseObject = new ResponseObject();		
		this.requiredData = new String[0];
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
	protected boolean validate(final RequestObject requestObject) {
		final JsonObject data = requestObject.getData();

    	// Verification des champs requis
    	for(String champ : requiredData){
    		if(data.get(champ) == null || data.get(champ).getAsString().isEmpty()){
    			ServiceUtils.logger.error("Service " +requestObject.getNomService()+ ": champ " +champ+ " requis manquant.");
    			return false;
    		}
    	}
		return true;
	}
    

    /**
     * @param requestObject
     * @param resp 
     * @param req 
     * @return
     */
    protected ResponseObject execute(final RequestObject requestObject, ServletRequest servletRequest){
		if (validate(requestObject)) {
			serviceLogic(requestObject, servletRequest);
		}else{
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Champ requis manquant", null);
		}
		return this.responseObject;
    }
    
    
    /**
     * Contient l'ensemble des traitements lies au service.
     * @param requestObject : donnees envoyees par le client
     * @param req 
     * @return ResponseObject : reponse du service
     */
    abstract public ResponseObject serviceLogic(final RequestObject requestObject, ServletRequest servletRequest);
    
	
	
	
	
	
}
