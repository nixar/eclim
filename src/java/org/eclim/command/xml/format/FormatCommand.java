/**
 * Copyright (c) 2005 - 2006
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclim.command.xml.format;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import javax.xml.transform.sax.SAXSource;

import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;

import org.apache.commons.lang.StringUtils;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

import org.eclim.command.AbstractCommand;
import org.eclim.command.CommandLine;
import org.eclim.command.Options;

import org.w3c.dom.Document;

import org.xml.sax.InputSource;

/**
 * Command to format an xml file.
 *
 * @author Eric Van Dewoestine (ervandew@yahoo.com)
 * @version $Revision$
 */
public class FormatCommand
  extends AbstractCommand
{
  /**
   * {@inheritDoc}
   */
  public Object execute (CommandLine _commandLine)
  {
    FileInputStream in = null;
    try{
      String file = _commandLine.getValue(Options.FILE_OPTION);
      int lineWidth = _commandLine.getIntValue(Options.LINE_WIDTH_OPTION);
      int indent = _commandLine.getIntValue(Options.INDENT_OPTION);

      // xerces
      Document document = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder().parse(new File(file));
      OutputFormat format = new OutputFormat(document);
      format.setLineWidth(lineWidth);
      format.setIndenting(true);
      format.setIndent(indent);

      XMLSerializer serializer = new XMLSerializer(System.out, format);
      serializer.serialize(document);

      // javax.xml.transform (indentation issues)
      /*TransformerFactory factory =TransformerFactory.newInstance();
      factory.setAttribute("indent-number", Integer.valueOf(indent));
      Transformer serializer = factory.newTransformer();
      serializer.setOutputProperty(OutputKeys.INDENT, "yes");
      serializer.setOutputProperty(
          "{http://xml.apache.org/xslt}indent-amount", indent);
      in = new FileInputStream(file);
      serializer.transform(new SAXSource(new InputSource(in)),
          new StreamResult(System.out));*/

      return StringUtils.EMPTY;
    }catch(Throwable t){
      return t;
    }finally{
      IOUtils.closeQuietly(in);
    }
  }
}
