package com.structurizr.example.core;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.documentation.Format;
import com.structurizr.documentation.StructurizrDocumentation;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.util.ImageUtils;
import com.structurizr.view.*;

import java.io.File;

/**
 * <p>
 *   This is a simple example that illustrates the corporate branding features of Structurizr, including:
 * </p>
 *
 * <ul>
 *   <li>A colour scheme, which is applied to model elements and documentation navigation.</li>
 *   <li>A logo, which is included in diagrams and documentation.</li>
 * </ul>
 *
 * <p>
 *   You can see the live workspace at https://structurizr.com/share/35031
 * </p>
 */
public class CorporateBranding {

    private static final long WORKSPACE_ID = 35031;
    private static final String API_KEY = "";
    private static final String API_SECRET = "";

    public static void main(String[] args) throws Exception {
        Workspace workspace = new Workspace("Corporate Branding", "This is a model of my software system.");
        Model model = workspace.getModel();

        Person user = model.addPerson("User", "A user of my software system.");
        SoftwareSystem softwareSystem = model.addSoftwareSystem("Software System", "My software system.");
        user.uses(softwareSystem, "Uses");

        ViewSet views = workspace.getViews();
        SystemContextView contextView = views.createSystemContextView(softwareSystem, "SystemContext", "An example of a System Context diagram.");
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();

        Styles styles = views.getConfiguration().getStyles();
        styles.addElementStyle(Tags.PERSON).shape(Shape.Person);

        StructurizrDocumentation documentation = new StructurizrDocumentation(workspace);
        documentation.addContextSection(softwareSystem, Format.Markdown, "Here is some context about the software system...\n\n![](embed:SystemContext)");
        documentation.addQualityAttributesSection(softwareSystem, Format.Markdown, "Here is some information about the quality attributes...");
        documentation.addSoftwareArchitectureSection(softwareSystem, Format.Markdown, "Here is some information about the software architecture...");
        documentation.addOperationAndSupportSection(softwareSystem, Format.Markdown, "Here is some information about how to operate and support the software...");
        documentation.addDecisionLog(softwareSystem, Format.Markdown, "Here is some information about the decisions made...");

        Branding branding = views.getConfiguration().getBranding();
        branding.setColor1(new ColorPair("#02172C", "#ffffff"));
        branding.setColor2(new ColorPair("#08427B", "#ffffff"));
        branding.setColor3(new ColorPair("#1168BD", "#ffffff"));
        branding.setColor4(new ColorPair("#438DD5", "#ffffff"));
        branding.setColor5(new ColorPair("#85BBF0", "#ffffff"));
        branding.setLogo(ImageUtils.getImageAsDataUri(new File("./structurizr-examples/src/com/structurizr/example/core/structurizr.png")));

        StructurizrClient structurizrClient = new StructurizrClient(API_KEY, API_SECRET);
        structurizrClient.putWorkspace(WORKSPACE_ID, workspace);
    }

}
