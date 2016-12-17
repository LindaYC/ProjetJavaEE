/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerBean;

/**
 *
 * @author MLBaiche
 */

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Map;
import javafx.scene.control.Label;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ManagedBean {
    /**
     * Return the FacesContext instance for the request that is being processed
     * by the current thread.
     * 
     * @return
     */
    protected final FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Return the ExternalContext instance for the requested FacesContext
     * instance.
     * 
     * @return
     */
    protected final ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    /**
     * Return the session instance associated with the current request.
     * 
     * @return
     */
    protected final HttpSession getHttpSession() {
        return (HttpSession) getExternalContext().getSession(false);
    }

    /**
     * Return the http request instance.
     * 
     * @return
     */
    protected final HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    /**
     * Return the servlet context instance.
     * 
     * @return
     */
    protected final ServletContext getServletContext() {
        return (ServletContext) getExternalContext().getContext();
    }

    /**
     * Return the Application instance associated with this web application.
     * 
     * @return
     */
    protected final Application getApplication() {
        return getFacesContext().getApplication();
    }

    /**
     * Return the current Label RessourceBundle
     * 
     * @return
     */
    protected final Label getLabelBundle() {
        return null;
    }

    /**
     * Return the string label for the given key from the label resource bundle.
     * 
     * @param key
     * @return
     */
    protected final String getLabel(String key) {
        return null;
    }

    /**
     * Return the formatted string label for the given key from the label
     * resource bundle.
     * 
     * @param key
     * @param parameters
     * @return
     */
    protected final String getFormattedLabel(String key, Object... parameters) {
        String pattern = getLabel(key).replaceAll("(?<!')'(?!')", "''");
        return MessageFormat.format(pattern, parameters);
    }

    /**
     * Redirect a request to the specified URL, and cause the responseComplete()
     * method to be called on the FacesContext instance for the current request.
     * 
     * @param urlRedirect
     * @throws IOException
     */
    protected final void redirect(String urlRedirect) throws IOException {
        getExternalContext().redirect(urlRedirect);
    }

    /**
     * Return an InputStream for an application resource mapped to the specified
     * path, if it exists; otherwise, return null.
     * 
     * @param paramString
     * @return
     */
    protected final InputStream getResourceAsStream(String paramString) {
        return getExternalContext().getResourceAsStream(paramString);
    }

    /**
     * Append a FacesMessage to the set of info message associated with the
     * specified client identifier linked with the current event.
     * 
     * @param event
     * @param key
     *            entry define on the label.properties
     */
    protected final void addMessage(ActionEvent event, String key) {
        addMessage(event, FacesMessage.SEVERITY_INFO, key, null);
    }

    /**
     * Append a FacesMessage to the set of message associated with the specified
     * client identifier linked with the current event.
     * 
     * @param event
     * @param severity
     * @param key
     */
    protected final void addMessage(ActionEvent event,
            FacesMessage.Severity severity, String key) {
        addMessage(event, severity, key, null);
    }

    /**
     * Append a FacesMessage to the set of error message associated with the
     * specified client identifier linked with the current event.
     * 
     * @param severity
     * @param key
     */
    protected final void addError(String key) {
        addMessage(null, FacesMessage.SEVERITY_ERROR, key, null);
    }

    /**
     * Append a FacesMessage to the set of message associated with the specified
     * client identifier linked with the current event.
     * 
     * @param severity
     * @param key
     */
    protected final void addMessage(FacesMessage.Severity severity, String key) {
        addMessage(null, severity, key, null);
    }

    /**
     * Append a FacesMessage to the set of message associated with the specified
     * client identifier linked with the current event.
     * 
     * @param severity
     * @param key
     */
    protected final void addMessage(FacesMessage.Severity severity, String key,
            String detail) {
        addMessage(null, severity, key, detail);
    }

    /**
     * Append a FacesMessage to the set of message associated with the specified
     * client identifier linked with the current event.
     * 
     * @param event
     * @param severity
     * @param key
     * @param detail
     */
    protected final void addMessage(ActionEvent event,
            FacesMessage.Severity severity, String key, String detail) {
        FacesMessage message = new FacesMessage(severity, getLabel(key), detail);
        addMessage(event == null ? null : event.getComponent().getId(), message);
    }

    /**
     * Append a FacesMessage to the set of messages associated with the
     * specified client identifier, if clientId is not null. If clientId is
     * null, this FacesMessage is assumed to not be associated with any specific
     * component instance.
     * 
     * @param clientId
     * @param message
     */
    protected final void addMessage(String clientId, FacesMessage message) {
        getFacesContext().addMessage(clientId, message);
    }

    /**
     * 
     * @return
     */
    protected final UIComponent getCurrentComponent() {
        return UIComponent.getCurrentComponent(getFacesContext());
    }
    
    protected final Map<String,String> getRequestParameterMap() {
        return getExternalContext().getRequestParameterMap();
    }
}
