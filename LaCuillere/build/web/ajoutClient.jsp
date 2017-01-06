<!-- Include du header -->
<jsp:include page="header.jsp" flush="true"/>

         <div class="page-wrapper">
            <div class="breadcrumb">
               <div class="container">
                  <ul>
                     <li><a href="index.jsp" class="active">Accueil</a></li>
                     <li>Inscription</li>
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
                                 <div class="row">
                                    <div class="form-group col-sm-6">
                                       <label for="exampleInputEmail1">Prénom</label>
                                       <input class="form-control" type="text" placeholder="Saisir prénom" id="example-text-input"> 
                                    </div>
                                    <div class="form-group col-sm-6">
                                       <label for="exampleInputEmail1">Nom</label>
                                       <input class="form-control" type="text" placeholder="Saisir nom" id="example-text-input-2"> 
                                    </div>
                                    <div class="form-group col-sm-6">
                                       <label for="exampleInputEmail1">Email</label>
                                       <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Saisir email"> <small id="emailHelp" class="form-text text-muted">Nous ne partagerons jamais votre email avec quelqu'un d'autre.</small> 
                                    </div>
                                    <div class="form-group col-sm-6">
                                       <label for="exampleInputEmail1">Téléphone</label>
                                       <input class="form-control" type="tel" placeholder="N°Tel" id="example-tel-input-3"> <small class="form-text text-muted">Nous ne partagerons jamais votre numéro de téléphone avec quelqu'un d'autre.</small> 
                                    </div>
                                    <div class="form-group col-sm-6">
                                       <label for="exampleInputPassword1">Mot de passe</label>
                                       <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Mot de passe" > 
                                    </div>
                                    <div class="form-group col-sm-6">
                                       <label for="exampleInputPassword1">Répéter le mot de passe</label>
                                       <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Mot de passe"> 
                                    </div>
                                 </div>
                                 <div class="row">
                                    <div class="col-sm-4">
                                       <p> <a href="#" class="btn theme-btn">S'inscrir</a> </p>
                                    </div>
                                 </div>
                              </form>
                           </div>
                           <!-- end: Widget -->
                        </div>
                        <!-- /REGISTER -->
                     </div>
                  </div>
               </div>
            </section>

<!-- Include du footer -->        
<jsp:include page="footer.jsp" flush="true"/>