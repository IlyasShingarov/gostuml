/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2024, Arnaud Roques
 *
 * Project Info:  https://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * https://plantuml.com/patreon (only 1$ per month!)
 * https://plantuml.com/paypal
 * 
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 *
 *
 */
package net.sourceforge.plantuml.gostdiagram;

import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.Colors;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.klimt.shape.URectangle;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;

/**
 * GOST terminator (start/end) shape that creates a stadium/oval
 * (rectangle with rounded ends) instead of a circle, following
 * GOST 19.701-90 standards for algorithm flowcharts.
 */
public class GostTerminator implements UDrawable {

	private static final int WIDTH = 60;
	private static final int HEIGHT = 30;

	private final ISkinParam skinParam;
	private final Style style;
	private final Colors colors;
	private final String label;

	public GostTerminator(ISkinParam skinParam, Style style, Colors colors, String label) {
		this.style = style;
		this.colors = colors;
		this.skinParam = skinParam;
		this.label = label;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(WIDTH, HEIGHT);
	}

	final public void drawU(UGraphic ug) {
		// Create a rectangle with rounded corners to form a stadium/oval shape
		// The radius should be half the height to create the stadium effect
		final double radius = HEIGHT / 2.0;
		final URectangle stadium = URectangle.build(WIDTH, HEIGHT).rounded(radius);

		HColor backColor = style.value(PName.BackGroundColor).asColor(skinParam.getIHtmlColorSet());
		HColor lineColor = style.value(PName.LineColor).asColor(skinParam.getIHtmlColorSet());
		
		if (colors.getColor(ColorType.BACK) != null) {
			lineColor = colors.getColor(ColorType.BACK);
			backColor = colors.getColor(ColorType.BACK);
		}

		final double shadowing = style.value(PName.Shadowing).asDouble();
		stadium.setDeltaShadow(shadowing);
		
		ug.apply(lineColor).apply(backColor.bg()).draw(stadium);
	}
}