package com.meankieli.dictionary;

import com.tickaroo.tikxml.TikXmlConfig;
import com.tickaroo.tikxml.XmlReader;
import com.tickaroo.tikxml.XmlWriter;
import com.tickaroo.tikxml.typeadapter.ChildElementBinder;
import com.tickaroo.tikxml.typeadapter.TypeAdapter;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

public class TagElement$$TypeAdapter implements TypeAdapter<TagElement> {
  private Map<String, ChildElementBinder<ValueHolder>> childElementBinders = new  HashMap<String, ChildElementBinder<ValueHolder>>();

  public TagElement$$TypeAdapter() {
    childElementBinders.put("n", new ChildElementBinder<ValueHolder>() {
      @Override
      public void fromXml(XmlReader reader, TikXmlConfig config, ValueHolder value) throws
          IOException {
        while(reader.hasAttribute()) {
          String attributeName = reader.nextAttributeName();
          if (config.exceptionOnUnreadXml() && !attributeName.startsWith("xmlns")) {
            throw new IOException("Unread attribute '"+ attributeName +"' at path "+ reader.getPath());
          }
          reader.skipAttributeValue();
        }
        value.name = reader.nextTextContent();
      }
    });
  }

  @Override
  public TagElement fromXml(XmlReader reader, TikXmlConfig config) throws IOException {
    ValueHolder value = new ValueHolder();
    while(reader.hasAttribute()) {
      String attributeName = reader.nextAttributeName();
      if (config.exceptionOnUnreadXml() && !attributeName.startsWith("xmlns")) {
        throw new IOException("Could not map the xml attribute with the name '"+attributeName+"' at path "+reader.getPath()+" to java class. Have you annotated such a field in your java class to map this xml attribute? Otherwise you can turn this error message off with TikXml.Builder().exceptionOnUnreadXml(false).build().");
      }
      reader.skipAttributeValue();
    }
    while(true) {
      if (reader.hasElement()) {
        reader.beginElement();
        String elementName = reader.nextElementName();
        ChildElementBinder<ValueHolder> childElementBinder = childElementBinders.get(elementName);
        if (childElementBinder != null) {
          childElementBinder.fromXml(reader, config, value);
          reader.endElement();
        } else if (config.exceptionOnUnreadXml()) {
          throw new IOException("Could not map the xml element with the tag name <" + elementName + "> at path '" + reader.getPath()+"' to java class. Have you annotated such a field in your java class to map this xml attribute? Otherwise you can turn this error message off with TikXml.Builder().exceptionOnUnreadXml(false).build().");
        } else {
          reader.skipRemainingElement();
        }
      } else if (reader.hasTextContent()) {
        if (config.exceptionOnUnreadXml()) {
          throw new IOException("Could not map the xml element's text content at path '"+reader.getPath()+" to java class. Have you annotated such a field in your java class to map the xml element's text content? Otherwise you can turn this error message off with TikXml.Builder().exceptionOnUnreadXml(false).build().");
        }
        reader.skipTextContent();
      } else {
        break;
      }
    }
    return new TagElement(value.name);
  }

  @Override
  public void toXml(XmlWriter writer, TikXmlConfig config, TagElement value,
      String overridingXmlElementTagName) throws IOException {
    if (value != null) {
      if (overridingXmlElementTagName == null) {
        writer.beginElement("s");
      } else {
        writer.beginElement(overridingXmlElementTagName);
      }
      if (value.getName() != null) {
        writer.beginElement("n");
        if (value.getName() != null) {
          writer.textContent(value.getName());
        }
        writer.endElement();
      }
      writer.endElement();
    }
  }

  static class ValueHolder {
    String name;
  }
}
