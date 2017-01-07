<!-- Include du header -->
<jsp:include page="header.jsp" flush="true"/>

<div class="page-wrapper">

    <div class="breadcrumb">
        <div class="container">
            <ul>
                <li><a href="#" class="active">Home</a></li>
                <li><a href="#">Espace Restaurateur</a></li>
                <li>Profile</li>
            </ul>
        </div>
    </div>
    <section class="contact-page inner-page">
        <div class="container">
            <div class="row">
                <!-- REGISTER -->
                <div class="col-md-8">
                    <div class="widget">
                        <div class="widget-body">
                            <form>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Nom *</label>
                                    <input class="form-control" type="text" value="Nom du restaurant" id="example-text-input"> 
                                </div>
                                
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Adresse *</label>
                                    <input class="form-control" type="text" value="Adresse du restaurant" id="example-text-input"> 
                                </div>
                                
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Telephone</label>
                                    <input class="form-control" type="tel" value="01 02 03 04 05" id="example-tel-input"> 
                                </div>
                                
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Email *</label>
                                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Saisir email"> 
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputEmail1">Heure Ouverture</label>
                                     <input type="time" class="form-control" id="Heure" placeholder="Heure">
                                </div>
                                
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Heure Fermeture</label>
                                    <input type="time" class="form-control" id="Heure" placeholder="Heure"> 
                                </div>
                                
                                <div class="form-group">
                                    <label>Jour d'ouverture</label>
                                    <br>
                                    <div class="btn-group" data-toggle="buttons">
                                        <label class="btn theme-btn active">
                                            <input type="checkbox" checked>Lun </label>
                                        <label class="btn theme-btn active">
                                            <input type="checkbox"> Mar </label>
                                        <label class="btn theme-btn active">
                                            <input type="checkbox"> Mer </label>
                                        <label class="btn theme-btn active">
                                            <input type="checkbox"> Jeu </label>
                                        <label class="btn btn-secondary">
                                            <input type="checkbox"> Ven </label>
                                        <label class="btn btn-secondary">
                                            <input type="checkbox"> Sam </label>
                                        <label class="btn btn-secondary">
                                            <input type="checkbox"> Dim </label>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="exampleSelect1">Catégorie</label>
                                    <select class="form-control" id="exampleSelect1">
                                        <option>Gastronomie</option>
                                        <option>Orientale</option>
                                        <option>Thailande</option>
                                        <option>Mexicain</option>
                                        <option>Italien</option>
                                    </select>
                                </div>
                                
                                <div class="form-group">
                                    <label for="exampleTextarea">Description Menu</label>
                                    <textarea class="form-control" id="exampleTextarea" rows="3"></textarea>
                                </div>
                                
                                <div class="form-group">
                                    <label for="exampleInputFile">Image restaurant</label>
                                    <input type="file" class="form-control-file" id="exampleInputFile" aria-describedby="fileHelp">  
                                </div>
                                <p>
                                    <button type="submit" class="btn theme-btn">Confirmer</button>
                                </p>
                            </form>
                        </div>
                    </div>
                    <!-- end: Widget -->
                </div>
                <!-- /REGISTER -->

            </div>
        </div>
    </section>

    <!-- Include du footer -->        
    <jsp:include page="footer.jsp" flush="true"/>