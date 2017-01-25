/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.sql.rowset.serial.SerialBlob;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author YC-Linda
 */
public class Restaurant {
    private int idRestaurant;
    private String nom;
    private Categorie categorie;
    private String adresse;
    private String numPhone;
    private String ville;
    private String email;
    private int capacite;
    private Time heureOuverture;
    private Time heureFermeture;
    private long taillePhoto;
    private int prixMoyen;
    private FileInputStream  packageBlob;
    private ByteArrayOutputStream packageBlobRead;

    public ByteArrayOutputStream getPackageBlobRead() {
        return packageBlobRead;
    }

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            return new DefaultStreamedContent(new ByteArrayInputStream(packageBlobRead.toByteArray()));
        }
    }

    
    public int getPrixMoyen() {
        return prixMoyen;
    }

    public void setPrixMoyen(int prixMoyen) {
        this.prixMoyen = prixMoyen;
    }
    

    public void setPackageBlobRead(ByteArrayOutputStream packageBlobRead) {
        this.packageBlobRead = packageBlobRead;
    }

    public long getTaillePhoto() {
        return taillePhoto;
    }

    public void setTaillePhoto(long taillePhoto) {
        this.taillePhoto = taillePhoto;
    }

    
    public FileInputStream getPackageBlob() {
        return packageBlob;
    }

    public void setPackageBlob(FileInputStream packageBlob) {
        this.packageBlob = packageBlob;
    }
     
    
    
    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumPhone() {
        return numPhone;
    }

    public void setNumPhone(String numPhone) {
        this.numPhone = numPhone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    /**
     * @return the heureOuverture
     */
    public Time getHeureOuverture() {
        return heureOuverture;
    }

    /**
     * @param heureOuverture the heureOuverture to set
     */
    public void setHeureOuverture(Time heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    /**
     * @return the heureFermeture
     */
    public Time getHeureFermeture() {
        return heureFermeture;
    }

    /**
     * @param heureFermeture the heureFermeture to set
     */
    public void setHeureFermeture(Time heureFermeture) {
        this.heureFermeture = heureFermeture;
    }

    public int getSizeImage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
