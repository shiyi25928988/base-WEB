package html.base.utils;

import j2html.attributes.Attr;
import j2html.tags.EmptyTag;

public final class EmptyTagUtil {
	
	private EmptyTagUtil() {}
	
	public static EmptyTag area()                                                   { return new EmptyTag("area"); }
	public static EmptyTag area(Attr.ShortForm shortAttr)                           { return Attr.addTo(new EmptyTag("area"), shortAttr); }

	public static EmptyTag base()                                                   { return new EmptyTag("base"); }
	public static EmptyTag base(Attr.ShortForm shortAttr)                           { return Attr.addTo(new EmptyTag("base"), shortAttr); }

	public static EmptyTag br()                                                     { return new EmptyTag("br"); }
	public static EmptyTag br(Attr.ShortForm shortAttr)                             { return Attr.addTo(new EmptyTag("br"), shortAttr); }

	public static EmptyTag col()                                                    { return new EmptyTag("col"); }
	public static EmptyTag col(Attr.ShortForm shortAttr)                            { return Attr.addTo(new EmptyTag("col"), shortAttr); }

	public static EmptyTag embed()                                                  { return new EmptyTag("embed"); }
	public static EmptyTag embed(Attr.ShortForm shortAttr)                          { return Attr.addTo(new EmptyTag("embed"), shortAttr); }

	public static EmptyTag hr()                                                     { return new EmptyTag("hr"); }
	public static EmptyTag hr(Attr.ShortForm shortAttr)                             { return Attr.addTo(new EmptyTag("hr"), shortAttr); }

	public static EmptyTag img()                                                    { return new EmptyTag("img"); }
	public static EmptyTag img(Attr.ShortForm shortAttr)                            { return Attr.addTo(new EmptyTag("img"), shortAttr); }

	public static EmptyTag input()                                                  { return new EmptyTag("input"); }
	public static EmptyTag input(Attr.ShortForm shortAttr)                          { return Attr.addTo(new EmptyTag("input"), shortAttr); }

	public static EmptyTag keygen()                                                 { return new EmptyTag("keygen"); }
	public static EmptyTag keygen(Attr.ShortForm shortAttr)                         { return Attr.addTo(new EmptyTag("keygen"), shortAttr); }

	public static EmptyTag link()                                                   { return new EmptyTag("link"); }
	public static EmptyTag link(Attr.ShortForm shortAttr)                           { return Attr.addTo(new EmptyTag("link"), shortAttr); }

	public static EmptyTag meta()                                                   { return new EmptyTag("meta"); }
	public static EmptyTag meta(Attr.ShortForm shortAttr)                           { return Attr.addTo(new EmptyTag("meta"), shortAttr); }

	public static EmptyTag param()                                                  { return new EmptyTag("param"); }
	public static EmptyTag param(Attr.ShortForm shortAttr)                          { return Attr.addTo(new EmptyTag("param"), shortAttr); }

	public static EmptyTag source()                                                 { return new EmptyTag("source"); }
	public static EmptyTag source(Attr.ShortForm shortAttr)                         { return Attr.addTo(new EmptyTag("source"), shortAttr); }

	public static EmptyTag track()                                                  { return new EmptyTag("track"); }
	public static EmptyTag track(Attr.ShortForm shortAttr)                          { return Attr.addTo(new EmptyTag("track"), shortAttr); }

	public static EmptyTag wbr()                                                    { return new EmptyTag("wbr"); }
	public static EmptyTag wbr(Attr.ShortForm shortAttr)                            { return Attr.addTo(new EmptyTag("wbr"), shortAttr); }
}
