<!-- Include du header -->
<link href="css/bootstrap2.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style2.css" rel="stylesheet" type="text/css" media="all" />	
<script src="js/jquery2.min.js"></script>

<jsp:include page="header.jsp" flush="true"/>

</br></br>
<div class="container">
    <div class="check-sec">	 
        <div class="col-md-3 cart-total">
            <a class="continue"> Mon profil</a>
            <div class="price-details">
                <h3>Mes reservations</h3>
                <h3>Mes avis</h3>
                <h3>Mes favoris</h3>
                <h3>Mon espace fid�lit�</h3>
                <h3>Mes informations</h3>
            </div>	
            <a class="order" href="#">Se d�connecter</a>
        </div>
        <div class="col-md-9 cart-items">
            <h1>Mes reservations</h1>
            <script>$(document).ready(function (c) {
                    $('.close1').on('click', function (c) {
                        $('.cart-header').fadeOut('slow', function (c) {
                            $('.cart-header').remove();
                        });
                    });
                });
            </script>

            <div class="cart-header">
                <div class="close1" style="margin-top:-5%"> </div>
                <div class="cart-sec simpleCart_shelfItem">
                    <div class="cart-item cyc">
                        <img src="images/GeorgeV-1.jpg" class="img-responsive" alt=""/>
                    </div>
                    <div class="cart-item-info" style="margin-top:0%">
                        <h3><a href="#">Pizza Piave</a><span>1, rue Marechal Foch 75100 Paris</span></h3>
                        <ul class="qty">
                            <li>01 Janvier 2017</li>
                            <li><p>|</p></li>
                            <li><p>20:00</p></li>
                            <li><p>|</p></li>
                            <li><p>3 personnes</p></li>
                            <li><p>Statut : En cours</p></li>
                            <li><p>|</p></li>
                            <li><p>Annuler votre reservation</p></li>
                        </ul>							
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>

            <script>$(document).ready(function (c) {
                    $('.close2').on('click', function (c) {
                        $('.cart-header2').fadeOut('slow', function (c) {
                            $('.cart-header2').remove();
                        });
                    });
                });
            </script>
            <div class="cart-header">
                <div class="close1" style="margin-top:-5%"> </div>
                <div class="cart-sec simpleCart_shelfItem">
                    <div class="cart-item cyc">
                        <img src="images/GeorgeV-1.jpg" class="img-responsive" alt=""/>
                    </div>
                    <div class="cart-item-info" style="margin-top:0%">
                        <h3><a href="#">Pizza Piave</a><span>1, rue Marechal Foch 75100 Paris</span></h3>
                        <ul class="qty">
                            <li>01 Janvier 2017</li>
                            <li><p>|</p></li>
                            <li><p>20:00</p></li>
                            <li><p>|</p></li>
                            <li><p>3 personnes</p></li>
                            <li><p>Statut: En cours</p></li>
                        </ul>							
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <div class="clearfix"> </div>
    </div>
</div>         
<!-- Include du footer -->        
<jsp:include page="footer.jsp" flush="true"/>