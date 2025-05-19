package com.meankieli.dictionary;

import com.tickaroo.tikxml.TikXmlConfig;
import com.tickaroo.tikxml.XmlReader;
import com.tickaroo.tikxml.XmlWriter;
import com.tickaroo.tikxml.typeadapter.ChildElementBinder;
import com.tickaroo.tikxml.typeadapter.TypeAdapter;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeftElement$$TypeAdapter implements TypeAdapter<LeftElement> {
  private Map<String, ChildElementBinder<ValueHolder>> childElementBinders = new  HashMap<String, ChildElementBinder<ValueHolder>>();

  public LeftElement$$TypeAdapter() {
    childElementBinders.put("s", new ChildElementBinder<ValueHolder>() {
      @Override
      public void fromXml(XmlReader reader, TikXmlConfig config, ValueHolder value) throws
          IOException {
        if (value.tags == null) {
          value.tags = new ArrayList<TagElement>();
        }
        value.tags.add((TagElement) config.getTypeAdapter(TagElement.class).fromXml(reader, config) );
      }
    });
    childElementBinders.put("text", new ChildElementBinder<ValueHolder>() {
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
        value.text = reader.nextTextContent();
      }
    });
  }

  @Override
  public LeftElement fromXml(XmlReader reader, TikXmlConfig config) throws IOException {
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
    return new LeftElement(value.text, value.tags);
  }

  @Override
  public void toXml(XmlWriter writer, TikXmlConfig config, LeftElement value,
      String overridingXmlElementTagName) throws IOException {
    if (value != null) {
      if (overridingXmlElementTagName == null) {
        writer.beginElement("l");
      } else {
        writer.beginElement(overridingXmlElementTagName);
      }
      if (value.getTags() != null) {
        List<TagElement> list = value.getTags();
        int listSize = list.size();
        for (int i =0; i<listSize; i++) {
          TagElement item = list.get(i);
          config.getTypeAdapter(TagElement.class).toXml(writer, config, item, "s");
        }
      }
      if (value.getText() != null) {
        writer.beginElement("text");
        if (value.getText() != null) {
          writer.textContent(value.getText());
        }
        writer.endElement();
      }
      writer.endElement();
    }
  }

  static class ValueHolder {
    String text;

    List<TagElement> tags;
  }
}
