/*
 * Copyright (C) 2020 Emweb bv, Herent, Belgium.
 *
 * See the LICENSE file for terms of use.
 */
package eu.webtoolkit.jwt;

import eu.webtoolkit.jwt.chart.*;
import eu.webtoolkit.jwt.servlet.*;
import eu.webtoolkit.jwt.utils.*;
import java.io.*;
import java.lang.ref.*;
import java.time.*;
import java.util.*;
import java.util.regex.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Theme based on the Bootstrap 5 CSS framework.
 *
 * <p>This theme implements support for building a JWt web application that uses Bootstrap 5 as a
 * theme for its (layout and) styling.
 *
 * <p>Using this theme, various widgets provided by the library are rendered using markup that is
 * compatible with Bootstrap 5. The bootstrap theme is also extended with a proper (compatible)
 * styling of widgets for which bootstrap does not provide styling (table views, tree views,
 * sliders, etc...).
 *
 * <p>By default, the theme will use CSS and JavaScript resources that are shipped together with the
 * JWt distribution, but you can replace the CSS with custom-built CSS by reimplementing {@link
 * WBootstrap5Theme#getStyleSheets() getStyleSheets()}.
 *
 * <p>Although this theme styles individual widgets correctly, for your web application&apos;s
 * layout you are recommended to use {@link WTemplate} in conjunction with Bootstrap&apos;s CSS
 * classes. For this we refer to Bootstrap&apos;s documentation at <a
 * href="https://getbootstrap.com">https://getbootstrap.com</a>.
 *
 * <p>
 *
 * @see WApplication#setTheme(WTheme theme)
 */
public class WBootstrap5Theme extends WTheme {
  private static Logger logger = LoggerFactory.getLogger(WBootstrap5Theme.class);

  /** Constructor. */
  public WBootstrap5Theme() {
    super();
  }

  public void init(WApplication app) {
    app.getBuiltinLocalizedStrings().useBuiltin(WtServlet.BootstrapTheme_xml);
    app.getBuiltinLocalizedStrings().useBuiltin(WtServlet.Bootstrap5Theme_xml);
    app.require(this.getResourcesUrl() + "js/bootstrap.bundle.min.js");
    app.loadJavaScript("js/Bootstrap5Theme.js", wtjs3());
    WString v = app.metaHeader(MetaHeaderType.Meta, "viewport");
    if ((v.length() == 0)) {
      app.addMetaHeader("viewport", "width=device-width, initial-scale=1");
    }
  }

  public String getName() {
    return "bootstrap5";
  }

  public String getResourcesUrl() {
    return WApplication.getRelativeResourcesUrl() + "themes/bootstrap/5/";
  }

  public List<WLinkedCssStyleSheet> getStyleSheets() {
    List<WLinkedCssStyleSheet> result = new ArrayList<WLinkedCssStyleSheet>();
    final String themeDir = this.getResourcesUrl();
    result.add(new WLinkedCssStyleSheet(new WLink(themeDir + "css/bootstrap.min.css")));
    result.add(new WLinkedCssStyleSheet(new WLink(themeDir + "wt.css")));
    return result;
  }

  public void apply(WWidget widget, WWidget child, int widgetRole) {
    if (!widget.isThemeStyleEnabled()) {
      return;
    }
    switch (widgetRole) {
      case WidgetThemeRole.MenuItemIcon:
        child.addStyleClass("Wt-icon");
        break;
      case WidgetThemeRole.MenuItemCheckBox:
        child.setStyleClass("Wt-chkbox");
        ((WFormWidget) child).getLabel().addStyleClass("form-checkbox");
        break;
      case WidgetThemeRole.MenuItemClose:
        child.addStyleClass("close");
        ((WText) child).setText("&times;");
        break;
      case WidgetThemeRole.DialogContent:
        child.addStyleClass("modal-content");
        break;
      case WidgetThemeRole.DialogCoverWidget:
        child.addStyleClass("modal-backdrop in");
        child.setAttributeValue("style", "opacity:0.5");
        break;
      case WidgetThemeRole.DialogTitleBar:
        child.addStyleClass("modal-header");
        break;
      case WidgetThemeRole.DialogBody:
        child.addStyleClass("modal-body");
        break;
      case WidgetThemeRole.DialogFooter:
        child.addStyleClass("modal-footer");
        break;
      case WidgetThemeRole.DialogCloseIcon:
        child.addStyleClass("close");
        ((WText) child).setText("&times;");
        break;
      case WidgetThemeRole.TableViewRowContainer:
        {
          WAbstractItemView view = ObjectUtils.cast(widget, WAbstractItemView.class);
          child.toggleStyleClass("Wt-striped", view.hasAlternatingRowColors());
          break;
        }
      case WidgetThemeRole.DatePickerPopup:
        child.addStyleClass("Wt-datepicker");
        break;
      case WidgetThemeRole.DatePickerIcon:
        {
          WImage icon = ObjectUtils.cast(child, WImage.class);
          icon.setImageLink(new WLink(this.getResourcesUrl() + "calendar-date.svg"));
          icon.setVerticalAlignment(AlignmentFlag.Middle);
          icon.resize(new WLength(20), new WLength(20));
          icon.setMargin(new WLength(5), EnumSet.of(Side.Left));
          icon.addStyleClass("Wt-datepicker-icon");
          break;
        }
      case WidgetThemeRole.TimePickerPopup:
        child.addStyleClass("Wt-timepicker");
        break;
      case WidgetThemeRole.PanelTitle:
        {
          WPanel panel = ObjectUtils.cast(widget, WPanel.class);
          if (panel != null && panel.isCollapsible()) {
            child.addStyleClass("accordion-item");
          }
          break;
        }
      case WidgetThemeRole.PanelTitleBar:
        {
          WPanel panel = ObjectUtils.cast(widget, WPanel.class);
          if (panel != null && panel.isCollapsible()) {
            child.addStyleClass("accordion-header");
            child.removeStyleClass("card-header");
          } else {
            child.addStyleClass("card-header");
          }
          break;
        }
      case WidgetThemeRole.PanelCollapseButton:
        {
          WPanel panel = ObjectUtils.cast(widget, WPanel.class);
          if (panel != null && panel.isCollapsible()) {
            child.addStyleClass("accordion-button");
          }
          break;
        }
      case WidgetThemeRole.PanelBody:
        {
          WPanel panel = ObjectUtils.cast(widget, WPanel.class);
          if (panel != null && panel.isCollapsible()) {
            child.addStyleClass("accordion-collapse collapse show");
          } else {
            child.addStyleClass("card-body");
          }
          break;
        }
      case WidgetThemeRole.PanelBodyContent:
        {
          WPanel panel = ObjectUtils.cast(widget, WPanel.class);
          if (panel != null && panel.isCollapsible()) {
            child.addStyleClass("accordion-body");
          }
          break;
        }
      case WidgetThemeRole.InPlaceEditing:
        child.addStyleClass("input-group");
        break;
      case WidgetThemeRole.InPlaceEditingButton:
        child.addStyleClass("btn-outline-secondary");
        break;
      case WidgetThemeRole.NavCollapse:
        child.addStyleClass("navbar-collapse collapse");
        break;
      case WidgetThemeRole.NavBrand:
        child.addStyleClass("navbar-brand");
        break;
      case WidgetThemeRole.NavbarSearchForm:
        child.addStyleClass("d-flex");
        break;
      case WidgetThemeRole.NavbarMenu:
        child.addStyleClass("navbar-nav");
        break;
      case WidgetThemeRole.NavbarBtn:
        child.addStyleClass("navbar-toggler");
        break;
      case WidgetThemeRole.TimePickerPopupContent:
        child.addStyleClass("d-flex");
        break;
      default:
        if (child.hasStyleClass("form-inline")) {
          child.removeStyleClass("form-inline");
          child.addStyleClass("row");
        }
        break;
    }
  }

  public void apply(WWidget widget, final DomElement element, int elementRole) {
    boolean creating = element.getMode() == DomElement.Mode.Create;
    if (!widget.isThemeStyleEnabled()) {
      return;
    }
    {
      WPopupWidget popup = ObjectUtils.cast(widget, WPopupWidget.class);
      if (popup != null) {
        WDialog dialog = ObjectUtils.cast(widget, WDialog.class);
        if (!(dialog != null)) {
          element.addPropertyWord(Property.Class, "dropdown-menu");
        }
      }
    }
    switch (element.getType()) {
      case A:
        {
          if (creating && ObjectUtils.cast(widget, WPushButton.class) != null) {
            element.addPropertyWord(Property.Class, classBtn(widget));
          }
          WPushButton btn = ObjectUtils.cast(widget, WPushButton.class);
          if (creating && btn != null && btn.isDefault()) {
            element.addPropertyWord(Property.Class, "btn-primary");
          }
          WMenuItem item = ObjectUtils.cast(widget.getParent(), WMenuItem.class);
          if (item != null) {
            element.addPropertyWord(Property.Class, "nav-link");
          }
          if (element.getAttribute("href").length() == 0
              && element.getProperty(Property.Class).length() == 0) {
            element.addPropertyWord(Property.Class, "dropdown-item");
          }
          break;
        }
      case BUTTON:
        {
          WPushButton button = ObjectUtils.cast(widget, WPushButton.class);
          if (button != null) {
            if (creating && button.isDefault()) {
              element.addPropertyWord(Property.Class, "btn btn-primary");
            } else {
              if (creating) {
                element.addPropertyWord(Property.Class, classBtn(widget));
              }
            }
            if (!button.getLink().isNull()) {
              logger.error(
                  new StringWriter()
                      .append(
                          "Cannot use WPushButton::setLink() after the button has been rendered with WBootstrapTheme")
                      .toString());
            }
          }
          break;
        }
      case DIV:
        {
          WDialog dialog = ObjectUtils.cast(widget, WDialog.class);
          if (dialog != null) {
            element.addPropertyWord(Property.Class, "modal-dialog Wt-dialog");
            return;
          }
          WPanel panel = ObjectUtils.cast(widget, WPanel.class);
          if (panel != null) {
            if (panel.isCollapsible()) {
              element.addPropertyWord(Property.Class, "accordion-item");
            } else {
              element.addPropertyWord(Property.Class, "card");
            }
            return;
          }
          WProgressBar bar = ObjectUtils.cast(widget, WProgressBar.class);
          if (bar != null) {
            switch (elementRole) {
              case ElementThemeRole.MainElement:
                element.addPropertyWord(Property.Class, "progress");
                break;
              case ElementThemeRole.ProgressBarBar:
                element.addPropertyWord(Property.Class, "progress-bar");
                break;
            }
            return;
          }
          WGoogleMap map = ObjectUtils.cast(widget, WGoogleMap.class);
          if (map != null) {
            element.addPropertyWord(Property.Class, "Wt-googlemap");
            return;
          }
          WNavigationBar navBar = ObjectUtils.cast(widget, WNavigationBar.class);
          if (navBar != null) {
            element.addPropertyWord(Property.Class, "navbar");
            if (!hasNavbarExpandClass(navBar)) {
              element.addPropertyWord(Property.Class, "navbar-expand-lg");
            }
            return;
          }
        }
        break;
      case LABEL:
        {
          if (elementRole == ElementThemeRole.ToggleButtonRole) {
            WCheckBox cb = ObjectUtils.cast(widget, WCheckBox.class);
            WRadioButton rb = null;
            if (cb != null) {
              element.addPropertyWord(Property.Class, "form-check");
            } else {
              rb = ObjectUtils.cast(widget, WRadioButton.class);
              if (rb != null) {
                element.addPropertyWord(Property.Class, "form-check");
              }
            }
            if ((cb != null || rb != null) && !widget.isInline()) {
              element.setType(DomElementType.DIV);
            } else {
              element.addPropertyWord(Property.Class, "form-check-inline");
            }
          } else {
            if (elementRole == ElementThemeRole.FormLabel) {
              element.addPropertyWord(Property.Class, "form-file-label");
            }
          }
        }
        break;
      case LI:
        {
          WMenuItem item = ObjectUtils.cast(widget, WMenuItem.class);
          if (item != null) {
            final boolean separator = item.isSeparator();
            final boolean sectionHeader = item.isSectionHeader();
            if (separator) {
              element.addPropertyWord(Property.Class, "dropdown-divider");
            }
            if (!separator && !sectionHeader) {
              element.addPropertyWord(Property.Class, "nav-item");
            }
            if (item.getMenu() != null) {
              if (ObjectUtils.cast(item.getParentMenu(), WPopupMenu.class) != null) {
                element.addPropertyWord(Property.Class, "dropdown");
              }
            }
          }
        }
        break;
      case INPUT:
        {
          if (elementRole == ElementThemeRole.ToggleButtonInput) {
            element.addPropertyWord(Property.Class, "form-check-input");
            break;
          } else {
            if (elementRole == ElementThemeRole.FileUploadInput) {
              element.addPropertyWord(Property.Class, "form-control");
              break;
            }
          }
          WAbstractToggleButton tb = ObjectUtils.cast(widget, WAbstractToggleButton.class);
          WSlider sl = ObjectUtils.cast(widget, WSlider.class);
          WFileUpload fu = ObjectUtils.cast(widget, WFileUpload.class);
          if (!(tb != null || sl != null || fu != null)) {
            element.addPropertyWord(Property.Class, "form-control");
          } else {
            if (sl != null) {
              element.addPropertyWord(Property.Class, "form-range");
              if (sl.getOrientation() == Orientation.Vertical) {
                element.addPropertyWord(Property.Class, "vertical-slider");
              }
            }
          }
          WAbstractSpinBox spinBox = ObjectUtils.cast(widget, WAbstractSpinBox.class);
          if (spinBox != null) {
            element.addPropertyWord(Property.Class, "Wt-spinbox");
            return;
          }
          WDateEdit dateEdit = ObjectUtils.cast(widget, WDateEdit.class);
          if (dateEdit != null) {
            element.addPropertyWord(Property.Class, "Wt-dateedit");
            return;
          }
          WTimeEdit timeEdit = ObjectUtils.cast(widget, WTimeEdit.class);
          if (timeEdit != null) {
            element.addPropertyWord(Property.Class, "Wt-timeedit");
            return;
          }
        }
        break;
      case SELECT:
        element.addPropertyWord(Property.Class, "form-select");
        break;
      case TEXTAREA:
        element.addPropertyWord(Property.Class, "form-control");
        break;
      case UL:
        {
          WPopupMenu popupMenu = ObjectUtils.cast(widget, WPopupMenu.class);
          if (popupMenu != null) {
            element.addPropertyWord(Property.Class, "dropdown-menu");
            if (popupMenu.getParentItem() != null
                && ObjectUtils.cast(popupMenu.getParentItem().getParentMenu(), WPopupMenu.class)
                    != null) {
              element.addPropertyWord(Property.Class, "submenu");
            }
          } else {
            WMenu menu = ObjectUtils.cast(widget, WMenu.class);
            if (menu != null) {
              if (element.getProperty(Property.Class).indexOf("navbar-nav") == -1) {
                element.addPropertyWord(Property.Class, "nav");
              }
              WTabWidget tabs = ObjectUtils.cast(menu.getParent().getParent(), WTabWidget.class);
              if (tabs != null) {
                element.addPropertyWord(Property.Class, "nav-tabs");
              }
            } else {
              WSuggestionPopup suggestions = ObjectUtils.cast(widget, WSuggestionPopup.class);
              if (suggestions != null) {
                element.addPropertyWord(Property.Class, "typeahead");
              }
            }
          }
          break;
        }
      case SPAN:
        {
          if (elementRole == ElementThemeRole.ToggleButtonSpan) {
            element.addPropertyWord(Property.Class, "form-check-label");
          } else {
            if (elementRole == ElementThemeRole.FormText) {
              element.addPropertyWord(Property.Class, "form-file-text");
            } else {
              if (elementRole == ElementThemeRole.FormButton) {
                element.addPropertyWord(Property.Class, "form-file-button");
              }
            }
          }
          WInPlaceEdit inPlaceEdit = ObjectUtils.cast(widget, WInPlaceEdit.class);
          if (inPlaceEdit != null) {
            element.addPropertyWord(Property.Class, "Wt-in-place-edit");
          } else {
            WDatePicker picker = ObjectUtils.cast(widget, WDatePicker.class);
            if (picker != null) {
              element.addPropertyWord(Property.Class, "Wt-datepicker");
            }
          }
        }
        break;
      case FORM:
        if (elementRole == ElementThemeRole.FileUploadForm) {
          element.addPropertyWord(Property.Class, "input-group mb-2");
          widget.removeStyleClass("form-control");
        }
        break;
      default:
        break;
    }
  }

  public void setDataTarget(WWidget widget, WWidget target) {
    widget.setAttributeValue("data-bs-toggle", "collapse");
    widget.setAttributeValue("data-bs-target", new WString("#{1}").arg(target.getId()).toString());
  }

  public String getDisabledClass() {
    return "disabled";
  }

  public String getActiveClass() {
    return "active";
  }

  public String utilityCssClass(int utilityCssClassRole) {
    switch (utilityCssClassRole) {
      case UtilityCssClassRole.ToolTipInner:
        return "tooltip-inner";
      case UtilityCssClassRole.ToolTipOuter:
        return "tooltip fade top in";
      default:
        return "";
    }
  }

  public boolean isCanStyleAnchorAsButton() {
    return true;
  }

  public void applyValidationStyle(
      WWidget widget, final WValidator.Result validation, EnumSet<ValidationStyleFlag> styles) {
    WApplication app = WApplication.getInstance();
    app.loadJavaScript("js/BootstrapValidate.js", wtjs1());
    app.loadJavaScript("js/BootstrapValidate.js", wtjs2());
    if (app.getEnvironment().hasAjax()) {
      StringBuilder js = new StringBuilder();
      js.append("Wt4_7_1.setValidationState(")
          .append(widget.getJsRef())
          .append(",")
          .append(validation.getState() == ValidationState.Valid ? 1 : 0)
          .append(",")
          .append(WString.toWString(validation.getMessage()).getJsStringLiteral())
          .append(",")
          .append(EnumUtils.valueOf(styles))
          .append(",")
          .append("'is-valid', 'is-invalid');");
      widget.doJavaScript(js.toString());
    } else {
      boolean validStyle =
          validation.getState() == ValidationState.Valid
              && styles.contains(ValidationStyleFlag.ValidStyle);
      boolean invalidStyle =
          validation.getState() != ValidationState.Valid
              && styles.contains(ValidationStyleFlag.InvalidStyle);
      widget.toggleStyleClass("is-valid", validStyle);
      widget.toggleStyleClass("is-invalid", invalidStyle);
    }
  }

  public boolean canBorderBoxElement(final DomElement element) {
    return true;
  }

  private static String classBtn(WWidget widget) {
    WPushButton button = ObjectUtils.cast(widget, WPushButton.class);
    return hasButtonStyleClass(widget) || button != null && button.isDefault()
        ? "btn"
        : "btn btn-secondary";
  }

  private static boolean hasButtonStyleClass(WWidget widget) {
    int size = btnClasses.length;
    for (int i = 0; i < size; ++i) {
      if (widget.hasStyleClass(btnClasses[i])) {
        return true;
      }
    }
    return false;
  }

  private static boolean hasNavbarExpandClass(WNavigationBar navigationBar) {
    String classesStr = navigationBar.getStyleClass();
    List<String> classes = new ArrayList<String>();
    classes = new ArrayList<String>(Arrays.asList(classesStr.split(" ")));
    for (String c : classes) {
      if (c.startsWith("navbar-expand-")) {
        return true;
      }
    }
    return false;
  }

  static WJavaScriptPreamble wtjs1() {
    return new WJavaScriptPreamble(
        JavaScriptScope.WtClassScope,
        JavaScriptObjectType.JavaScriptFunction,
        "validate",
        "function(a){var b;b=a.options?a.options.item(a.selectedIndex)==null?\"\":a.options.item(a.selectedIndex).text:a.value;b=a.wtValidate.validate(b);this.setValidationState(a,b.valid,b.message,1)}");
  }

  static WJavaScriptPreamble wtjs2() {
    return new WJavaScriptPreamble(
        JavaScriptScope.WtClassScope,
        JavaScriptObjectType.JavaScriptFunction,
        "setValidationState",
        "function(a,b,i,e){var j=b==1&&(e&2)!=0;e=b!=1&&(e&1)!=0;var d=$(a),c=\"Wt-valid\",k=\"Wt-invalid\",f=this.theme;if(typeof f===\"object\"){c=f.classes.valid;k=f.classes.invalid}d.toggleClass(c,j).toggleClass(k,e);var g,h;c=d.closest(\".control-group\");if(c.length>0){g=\"success\";h=\"error\"}else{c=d.closest(\".form-group\");if(c.length>0){g=\"has-success\";h=\"has-error\"}}if(c.length>0){if(d=c.find(\".Wt-validation-message\"))b?d.text(a.defaultTT):d.text(i); c.toggleClass(g,j).toggleClass(h,e)}if(typeof a.defaultTT===\"undefined\")a.defaultTT=a.getAttribute(\"title\")||\"\";b?a.setAttribute(\"title\",a.defaultTT):a.setAttribute(\"title\",i)}");
  }

  static WJavaScriptPreamble wtjs3() {
    return new WJavaScriptPreamble(
        JavaScriptScope.WtClassScope,
        JavaScriptObjectType.JavaScriptObject,
        "theme",
        "{classes:{valid:\"is-valid\",invalid:\"is-invalid\"},type:\"bootstrap\",version:5}");
  }

  private static String[] btnClasses = {
    "btn-primary",
    "btn-secondary",
    "btn-success",
    "btn-danger",
    "btn-warning",
    "btn-info",
    "btn-light",
    "btn-dark",
    "btn-link",
    "btn-outline-primary",
    "btn-outline-secondary",
    "btn-outline-success",
    "btn-outline-danger",
    "btn-outline-warning",
    "btn-outline-info",
    "btn-outline-light",
    "btn-outline-dark",
    "btn-close",
    "navbar-toggler",
    "accordion-button"
  };
}
