Tweak emitter
=============


According to the Visitor Design Pattern, a visitor implementation could be provided or extended.

The following example disables javascript scripting.

    public class NoJavascriptHtmlEmitter extends HtmlEmitter {
        public NoJavascriptHtmlEmitter(Appendable buffer) {
            super(buffer);
        }

        protected boolean isTagAllowed(Tag t) {
            return !t.getName().equals("script");
        }

        protected boolean isTagAttributeAllowed(TagAttribute a) {
            return !a.getName().startsWith("on");
        }

        @Override
        public void visit(Tag node) {
            if(isTagAllowed(node)){
                super.visit(node);
            }
        }

        @Override
        public void visit(TagAttribute node) {
            if(isTagAttributeAllowed(node)){
                super.visit(node);
            }
        }
    }
