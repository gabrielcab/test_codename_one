package com.mycompany.myapp;

import com.codename1.ui.events.ActionEvent;

public class MyForm extends com.codename1.ui.Form {
    public MyForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public MyForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

    public void onButton_1ActionEvent(ActionEvent ev) {
        new Tabbed().show();
    }

    //-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.components.SpanLabel gui_Span_Label_1 = new com.codename1.components.SpanLabel();
    private com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
    private com.codename1.components.SpanLabel gui_Span_Label_2 = new com.codename1.components.SpanLabel();
    private com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button_1.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();

            if(sourceComponent.getParent().getLeadParent() != null && (sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.MultiButton || sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.SpanButton)) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == gui_Button_1) {
                onButton_1ActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("MyForm");
        setName("MyForm");
        addComponent(gui_Span_Label_1);
        addComponent(gui_Text_Field_1);
        addComponent(gui_Span_Label_2);
        addComponent(gui_Text_Field_2);
        addComponent(gui_Button_1);
        gui_Span_Label_1.setText("Email");
        gui_Span_Label_1.setName("Span_Label_1");
        gui_Text_Field_1.setText("TextField");
        gui_Text_Field_1.setName("Text_Field_1");
        gui_Span_Label_2.setText("Mot De Passe");
        gui_Span_Label_2.setName("Span_Label_2");
        gui_Text_Field_2.setText("TextField");
        gui_Text_Field_2.setName("Text_Field_2");
        gui_Button_1.setText("Se Connecter");
        gui_Button_1.setName("Button_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
