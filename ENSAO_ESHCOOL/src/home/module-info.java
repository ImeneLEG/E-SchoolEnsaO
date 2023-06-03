module MyProject {
	requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
	 // Add this line to require the java.awt package
    // Add this line to require the com.itextpdf.text package
    //requires itextpdf; // Ajoutez ici la dépendance vers iTextPDF ou tout autre module requis

   // exports com.example.mypackage;
	//opens application to javafx.graphics, javafx.fxml , javafx.base;
	//opens home;
	opens controllers; // Remplacez nomDuPackage par le nom du package contenant vos fichiers FXML
    opens models;
    opens images;
    opens fxml;
    opens styling;
    opens utils;
    opens model;
    opens home to javafx.graphics, javafx.fxml,javafx.base;
   // exports model;
   // exports home; // Remplacez nomDuPackage par le nom du package contenant vos classes publiques à exposer
}
