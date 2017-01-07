<!-- Include du header -->
<jsp:include page="header.jsp" flush="true"/>

<div class="page-wrapper">

            <!-- start: Inner page hero -->
            <section class="bg-image space-md" data-image-src="images/contact.jpg">
                <div class="profile">
                    <div class="container">
                        <div class="row">
                            <div class="col-xs-12 col-sm-4  col-lg-4 profile-img">
                                <h1 class="font-white">Nous contacter !</h1> </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- end:Inner page hero -->
            <div class="breadcrumb">
                <div class="container">
                    <ul>
                        <li><a href="index.jsp" class="active">Accueil</a></li>
                        <li>Nous contacter</li>
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
                                    <!-- Formulaire de contact -->
                                    <div class="form-horizontal contact-form" role="form">
                                        <fieldset>
                                            <div class="row form-group">
                                                <div class="col-xs-6">
                                                    <input class="form-control" id="fname" name="fname" type="text" placeholder="Prénom *" required=""> </div>
                                                <div class="col-xs-6">
                                                    <input class="form-control" id="lname" name="lname" type="text" placeholder="Nom *" required=""> </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col-xs-12">
                                                    <input class="form-control" id="subject" name="subject" type="text" placeholder="Sujet *" required=""> </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col-xs-12">
                                                    <textarea class="form-control" id="message" name="message" rows="10" placeholder="Message *" required=""></textarea>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col-xs-12">
                                                    <button class="btn btn-lg theme-btn" type="submit">Envoyer</button>
                                                </div>
                                            </div>
                                        </fieldset>
                                    </div>
                                    <!-- Fin formulaire de contact -->
                                </div>
                            </div>
                            <!-- end: Widget -->
                        </div>
                        <!-- /REGISTER -->
                        <!-- WHY? -->
                        <div class="col-md-4">
                            <h4>Nous sommes là pour vous aider !</h4>
                            <p>Vous ne trouvez pas ce que vous cherchez ?</p>
                            <ul class="list-check list-unstyled">
                                <li>Mail</li>
                                <li>Chat en ligne</li>
                                <li>FAQ</li>
                            </ul>
                        </div>
                        <!-- /WHY? -->
                    </div>
                </div>
            </section>
</div>
<!-- Include du footer -->        
<jsp:include page="footer.jsp" flush="true"/>