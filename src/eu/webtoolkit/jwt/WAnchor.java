/*
 * Copyright (C) 2009 Emweb bvba, Leuven, Belgium.
 *
 * See the LICENSE file for terms of use.
 */
package eu.webtoolkit.jwt;

import java.util.BitSet;
import java.util.EnumSet;

/**
 * A widget that represents an HTML anchor (to link to other documents)
 * <p>
 * 
 * Use an anchor to link to another web page, document, internal application
 * path or a resource. The anchor may contain a label text, an image, or any
 * other widget (as it inherits from {@link WContainerWidget}). If you do not
 * want the application to terminate when the user follows the anchor, you must
 * use setTarget(TargetNewWindow). Even for non-HTML documents, this may be
 * important since pending AJAX requests are cancelled even if documents are not
 * served within the browser window in certain browsers.
 * <p>
 * The widget corresponds to the HTML <code>&lt;a&gt;</code> tag.
 * <p>
 * WAnchor is an {@link WWidget#setInline(boolean inlined) inline} widget.
 * <p>
 * <p>
 * <i><b>Note:</b>If you set a text or image using one of the API methods like
 * {@link WAnchor#setText(CharSequence text)} or
 * {@link WAnchor#setImage(WImage image)} or a constructor, you should not
 * attempt to remove all contents (using {@link WContainerWidget#clear()}, or
 * provide a layout (using {@link WContainerWidget#setLayout(WLayout layout)}),
 * as this will result in undefined behaviour. </i>
 * </p>
 */
public class WAnchor extends WContainerWidget {
	/**
	 * Create an anchor.
	 */
	public WAnchor(WContainerWidget parent) {
		super(parent);
		this.ref_ = "";
		this.resource_ = null;
		this.text_ = null;
		this.image_ = null;
		this.target_ = AnchorTarget.TargetSelf;
		this.flags_ = new BitSet();
		this.changeInternalPathJS_ = null;
		this.setInline(true);
	}

	/**
	 * Create an anchor.
	 * <p>
	 * Calls {@link #WAnchor(WContainerWidget parent)
	 * this((WContainerWidget)null)}
	 */
	public WAnchor() {
		this((WContainerWidget) null);
	}

	/**
	 * Create an anchor referring to a URL.
	 */
	public WAnchor(String ref, WContainerWidget parent) {
		super(parent);
		this.ref_ = ref;
		this.resource_ = null;
		this.text_ = null;
		this.image_ = null;
		this.target_ = AnchorTarget.TargetSelf;
		this.flags_ = new BitSet();
		this.changeInternalPathJS_ = null;
		this.setInline(true);
	}

	/**
	 * Create an anchor referring to a URL.
	 * <p>
	 * Calls {@link #WAnchor(String ref, WContainerWidget parent) this(ref,
	 * (WContainerWidget)null)}
	 */
	public WAnchor(String ref) {
		this(ref, (WContainerWidget) null);
	}

	/**
	 * Create an anchor referring to a resource.
	 * <p>
	 * A resource specifies application-dependent content, which may be
	 * generated by your application on demand.
	 * <p>
	 * The anchor does not assume ownership of the resource.
	 */
	public WAnchor(WResource resource, WContainerWidget parent) {
		super(parent);
		this.ref_ = "";
		this.resource_ = null;
		this.text_ = null;
		this.image_ = null;
		this.target_ = AnchorTarget.TargetSelf;
		this.flags_ = new BitSet();
		this.changeInternalPathJS_ = null;
		this.setInline(true);
		this.setResource(resource);
	}

	/**
	 * Create an anchor referring to a resource.
	 * <p>
	 * Calls {@link #WAnchor(WResource resource, WContainerWidget parent)
	 * this(resource, (WContainerWidget)null)}
	 */
	public WAnchor(WResource resource) {
		this(resource, (WContainerWidget) null);
	}

	/**
	 * Create an anchor referring to a URL, using a text message.
	 */
	public WAnchor(String ref, CharSequence text, WContainerWidget parent) {
		super(parent);
		this.ref_ = ref;
		this.resource_ = null;
		this.text_ = null;
		this.image_ = null;
		this.target_ = AnchorTarget.TargetSelf;
		this.flags_ = new BitSet();
		this.changeInternalPathJS_ = null;
		this.setInline(true);
		this.text_ = new WText(text, this);
	}

	/**
	 * Create an anchor referring to a URL, using a text message.
	 * <p>
	 * Calls
	 * {@link #WAnchor(String ref, CharSequence text, WContainerWidget parent)
	 * this(ref, text, (WContainerWidget)null)}
	 */
	public WAnchor(String ref, CharSequence text) {
		this(ref, text, (WContainerWidget) null);
	}

	/**
	 * Create an anchor reffering to a resource, using a text message.
	 * <p>
	 * A resource specifies application-dependent content, which may be
	 * generated by your application on demand.
	 * <p>
	 * The anchor does not assume ownership of the resource.
	 */
	public WAnchor(WResource resource, CharSequence text,
			WContainerWidget parent) {
		super(parent);
		this.ref_ = "";
		this.resource_ = null;
		this.text_ = null;
		this.image_ = null;
		this.target_ = AnchorTarget.TargetSelf;
		this.flags_ = new BitSet();
		this.changeInternalPathJS_ = null;
		this.setInline(true);
		this.text_ = new WText(text, this);
		this.setResource(resource);
	}

	/**
	 * Create an anchor reffering to a resource, using a text message.
	 * <p>
	 * Calls
	 * {@link #WAnchor(WResource resource, CharSequence text, WContainerWidget parent)
	 * this(resource, text, (WContainerWidget)null)}
	 */
	public WAnchor(WResource resource, CharSequence text) {
		this(resource, text, (WContainerWidget) null);
	}

	/**
	 * Create an anchor reffering to a URL, using an image.
	 * <p>
	 * Ownership of the image is transferred to the anchor.
	 */
	public WAnchor(String ref, WImage image, WContainerWidget parent) {
		super(parent);
		this.ref_ = ref;
		this.resource_ = null;
		this.text_ = null;
		this.image_ = null;
		this.target_ = AnchorTarget.TargetSelf;
		this.flags_ = new BitSet();
		this.changeInternalPathJS_ = null;
		this.setInline(true);
		this.image_ = image;
		if (this.image_ != null) {
			this.addWidget(this.image_);
		}
	}

	/**
	 * Create an anchor reffering to a URL, using an image.
	 * <p>
	 * Calls {@link #WAnchor(String ref, WImage image, WContainerWidget parent)
	 * this(ref, image, (WContainerWidget)null)}
	 */
	public WAnchor(String ref, WImage image) {
		this(ref, image, (WContainerWidget) null);
	}

	/**
	 * Create an anchor reffering to a resource, using an image.
	 * <p>
	 * A resource specifies application-dependent content, which may be
	 * generated by your application on demand.
	 * <p>
	 * The anchor does not assume ownership of the resource.
	 * <p>
	 * Ownership of the image is transferred to the anchor.
	 */
	public WAnchor(WResource resource, WImage image, WContainerWidget parent) {
		super(parent);
		this.ref_ = "";
		this.resource_ = null;
		this.text_ = null;
		this.image_ = null;
		this.target_ = AnchorTarget.TargetSelf;
		this.flags_ = new BitSet();
		this.changeInternalPathJS_ = null;
		this.setInline(true);
		this.image_ = image;
		if (this.image_ != null) {
			this.addWidget(this.image_);
		}
		this.setResource(resource);
	}

	/**
	 * Create an anchor reffering to a resource, using an image.
	 * <p>
	 * Calls
	 * {@link #WAnchor(WResource resource, WImage image, WContainerWidget parent)
	 * this(resource, image, (WContainerWidget)null)}
	 */
	public WAnchor(WResource resource, WImage image) {
		this(resource, image, (WContainerWidget) null);
	}

	public void remove() {
		/* delete this.changeInternalPathJS_ */;
		super.remove();
	}

	/**
	 * Set the destination URL.
	 * <p>
	 * This method should not be used when the anchor has been pointed to a
	 * dynamically generated resource using
	 * {@link WAnchor#setResource(WResource resource)}.
	 * <p>
	 * 
	 * @see WAnchor#setResource(WResource resource)
	 * @see WAnchor#setRefInternalPath(String path)
	 */
	public void setRef(String ref) {
		if (!this.flags_.get(BIT_REF_INTERNAL_PATH) && this.ref_.equals(ref)) {
			return;
		}
		this.flags_.clear(BIT_REF_INTERNAL_PATH);
		this.ref_ = ref;
		this.flags_.set(BIT_REF_CHANGED);
		this.repaint(EnumSet.of(RepaintFlag.RepaintPropertyIEMobile));
	}

	/**
	 * Set the destination URL to an internal path.
	 * <p>
	 * Sets the anchor to point to the internal path <i>path</i>. When the
	 * anchor is activated, the internal path is set to <i>path</i>, and the
	 * {@link WApplication#internalPathChanged()} signal is emitted.
	 * <p>
	 * This is the easiest way to let the application participate in browser
	 * history, and generate URLs that are bookmarkable and search engine
	 * friendly.
	 * <p>
	 * Internally, this method sets the destination URL using: <code>
   setRef(WApplication::instance()-&gt;bookmarkUrl(path)) 
  </code>
	 * <p>
	 * The {@link WInteractWidget#clicked()} signal is connected to a slot that
	 * changes the internal path by calling <code>
   WApplication::instance()-&gt;setInternalPath(ref(), true);
  </code>
	 * <p>
	 * 
	 * @see WAnchor#setRef(String ref)
	 * @see WAnchor#setResource(WResource resource)
	 * @see WApplication#getBookmarkUrl()
	 * @see WApplication#setInternalPath(String path, boolean emitChange)
	 */
	public void setRefInternalPath(String path) {
		if (this.flags_.get(BIT_REF_INTERNAL_PATH) && path.equals(this.ref_)) {
			return;
		}
		this.flags_.set(BIT_REF_INTERNAL_PATH);
		this.ref_ = path;
		this.flags_.set(BIT_REF_CHANGED);
		this.repaint(EnumSet.of(RepaintFlag.RepaintPropertyIEMobile));
	}

	/**
	 * Returns the destination URL.
	 * <p>
	 * When the anchor refers to a resource, the current resource URL is
	 * returned. When the anchor refers to an internal path, the internal path
	 * is returned. Otherwise, the URL is returned that was set using
	 * {@link WAnchor#setRef(String ref)}.
	 * <p>
	 * 
	 * @see WAnchor#setRef(String ref)
	 * @see WResource#generateUrl()
	 */
	public String getRef() {
		return this.ref_;
	}

	/**
	 * Set a destination resource.
	 * <p>
	 * A resource specifies application-dependent content, which may be
	 * generated by your application on demand.
	 * <p>
	 * This sets the <i>resource</i> as the destination of the anchor, and is an
	 * alternative to {@link WAnchor#setRef(String ref)}. The resource may be
	 * cleared by passing <i>resource</i> = 0.
	 * <p>
	 * The anchor does not assume ownership of the resource.
	 * <p>
	 * 
	 * @see WAnchor#setRef(String ref)
	 */
	public void setResource(WResource resource) {
		this.resource_ = resource;
		if (this.resource_ != null) {
			this.resource_.dataChanged().addListener(this,
					new Signal.Listener() {
						public void trigger() {
							WAnchor.this.resourceChanged();
						}
					});
			this.setRef(this.resource_.generateUrl());
		}
	}

	/**
	 * Returns the destination resource.
	 * <p>
	 * Returns 0 if no resource has been set.
	 * <p>
	 * 
	 * @see WAnchor#setResource(WResource resource)
	 */
	public WResource getResource() {
		return this.resource_;
	}

	/**
	 * Sets a text label.
	 * <p>
	 * If no text was previously set, a new {@link WText} widget is added using
	 * {@link WContainerWidget#addWidget(WWidget widget)}.
	 */
	public void setText(CharSequence text) {
		if (!(this.text_ != null)) {
			this.text_ = new WText(text, this);
		} else {
			if (!(text.length() == 0)) {
				this.text_.setText(text);
			} else {
				if (this.text_ != null)
					this.text_.remove();
				this.text_ = null;
			}
		}
	}

	/**
	 * Returns the label text.
	 * <p>
	 * Returns an empty string if no label was set.
	 * <p>
	 * 
	 * @see WAnchor#setText(CharSequence text)
	 */
	public WString getText() {
		if (this.text_ != null) {
			return this.text_.getText();
		} else {
			return empty;
		}
	}

	/**
	 * Configure text word wrapping.
	 * <p>
	 * When <i>on</i> is true, the text set with
	 * {@link WAnchor#setText(CharSequence text)} may be broken up over multiple
	 * lines. When <i>on</i> is false, the text will displayed on a single line,
	 * unless the text contains &lt;br /&gt; tags or other block-level tags.
	 * <p>
	 * The default value is true.
	 * <p>
	 * 
	 * @see WAnchor#hasWordWrap()
	 */
	public void setWordWrap(boolean on) {
		if (!(this.text_ != null)) {
			this.text_ = new WText(this);
		}
		this.text_.setWordWrap(on);
	}

	/**
	 * Returns whether the widget may break lines.
	 * <p>
	 * 
	 * @see WAnchor#setWordWrap(boolean on)
	 */
	public boolean hasWordWrap() {
		return this.text_ != null ? this.text_.isWordWrap() : true;
	}

	/**
	 * Set an image.
	 * <p>
	 * If an image was previously set, it is deleted. The <i>image</i> is added
	 * using {@link WContainerWidget#addWidget(WWidget widget)}.
	 * <p>
	 * Ownership of the image is transferred to the anchor.
	 */
	public void setImage(WImage image) {
		if (this.image_ != null) {
			if (this.image_ != null)
				this.image_.remove();
		}
		this.image_ = image;
		if (this.image_ != null) {
			this.addWidget(this.image_);
		}
	}

	/**
	 * Returns the image.
	 * <p>
	 * Returns 0 if no image is set.
	 * <p>
	 * 
	 * @see WAnchor#setImage(WImage image)
	 */
	public WImage getImage() {
		return this.image_;
	}

	/**
	 * Set the location where the referred content should be displayed.
	 * <p>
	 * By default, the referred content is displayed in the application (
	 * {@link AnchorTarget#TargetSelf}). When the destination is an HTML
	 * document, the application is replaced with the new document. When the
	 * reference is a document that cannot be displayed in the browser, it is
	 * offered for download or opened using an external program, depending on
	 * browser settings.
	 * <p>
	 * By setting <i>target</i> to {@link AnchorTarget#TargetNewWindow}, the
	 * destination is displayed in a new browser window or tab.
	 * <p>
	 * 
	 * @see WAnchor#getTarget()
	 */
	public void setTarget(AnchorTarget target) {
		if (this.target_ != target) {
			this.target_ = target;
			this.flags_.set(BIT_TARGET_CHANGED);
		}
	}

	/**
	 * Returns the location where the referred content should be displayed.
	 * <p>
	 * 
	 * @see WAnchor#setTarget(AnchorTarget target)
	 */
	public AnchorTarget getTarget() {
		return this.target_;
	}

	private static final int BIT_REF_INTERNAL_PATH = 0;
	private static final int BIT_REF_CHANGED = 1;
	private static final int BIT_TARGET_CHANGED = 2;
	private String ref_;
	private WResource resource_;
	private WText text_;
	private WImage image_;
	private AnchorTarget target_;
	private BitSet flags_;
	private JSlot changeInternalPathJS_;

	private void resourceChanged() {
		this.setRef(this.resource_.generateUrl());
	}

	protected void updateDom(DomElement element, boolean all) {
		if (this.flags_.get(BIT_REF_CHANGED) || all) {
			String url = "";
			if (this.flags_.get(BIT_REF_INTERNAL_PATH)) {
				WApplication app = WApplication.getInstance();
				url = app.getBookmarkUrl(this.ref_);
				if (app.getEnvironment().hasAjax()) {
					if (!(this.changeInternalPathJS_ != null)) {
						this.changeInternalPathJS_ = new JSlot();
						this.clicked().addListener(this.changeInternalPathJS_);
						this.clicked().setPreventDefault(true);
					}
					this.changeInternalPathJS_
							.setJavaScript("function(obj, event){window.location.hash='#"
									+ DomElement.urlEncodeS(this.ref_) + "';}");
					this.clicked().senderRepaint();
				}
			} else {
				url = this.ref_;
				/* delete this.changeInternalPathJS_ */;
				this.changeInternalPathJS_ = null;
			}
			element.setAttribute("href", fixRelativeUrl(url));
			this.flags_.clear(BIT_REF_CHANGED);
		}
		if (this.flags_.get(BIT_TARGET_CHANGED) || all) {
			switch (this.target_) {
			case TargetSelf:
				if (!all) {
					element.setAttribute("target", "_self");
				}
				break;
			case TargetThisWindow:
				element.setAttribute("target", "_top");
				break;
			case TargetNewWindow:
				element.setAttribute("target", "_blank");
			}
			this.flags_.clear(BIT_TARGET_CHANGED);
		}
		super.updateDom(element, all);
	}

	protected DomElementType getDomElementType() {
		return DomElementType.DomElement_A;
	}

	protected void propagateRenderOk(boolean deep) {
		this.flags_.clear(BIT_REF_CHANGED);
		this.flags_.clear(BIT_TARGET_CHANGED);
		super.propagateRenderOk(deep);
	}

	protected void enableAjax() {
		if (this.flags_.get(BIT_REF_INTERNAL_PATH)) {
			this.flags_.set(BIT_REF_CHANGED);
			this.repaint(EnumSet.of(RepaintFlag.RepaintPropertyIEMobile));
		}
		super.enableAjax();
	}

	protected static WString empty = new WString("");
}
