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
 * GOST-specific terminator shape according to GOST 19.701-90 standard.
 * Creates a stadium/oval shape (rounded rectangle) for start/end elements.
 */
public class GostTerminator implements UDrawable {

	private static final double WIDTH = 60;
	private static final double HEIGHT = 30;
	private static final double CORNER_RADIUS = HEIGHT / 2; // Makes it stadium-shaped

	private final ISkinParam skinParam;
	private final Style style;
	private final Colors colors;

	public GostTerminator(ISkinParam skinParam, Style style, Colors colors) {
		this.style = style;
		this.colors = colors;
		this.skinParam = skinParam;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(WIDTH, HEIGHT);
	}

	final public void drawU(UGraphic ug) {
		// Create stadium shape (rounded rectangle with semicircular ends)
		final URectangle terminator = URectangle.build(WIDTH, HEIGHT).rounded(CORNER_RADIUS);

		HColor backColor = style.value(PName.BackGroundColor).asColor(skinParam.getIHtmlColorSet());
		HColor lineColor = style.value(PName.LineColor).asColor(skinParam.getIHtmlColorSet());
		
		if (colors.getColor(ColorType.BACK) != null) {
			lineColor = colors.getColor(ColorType.BACK);
			backColor = colors.getColor(ColorType.BACK);
		}

		final double shadowing = style.value(PName.Shadowing).asDouble();

		terminator.setDeltaShadow(shadowing);
		ug.apply(lineColor).apply(backColor.bg()).draw(terminator);
	}

}