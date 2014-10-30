package com.nacion.android.nacioncostarica.views.content;

import android.content.Context;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;
import com.nacion.android.nacioncostarica.models.Article;
import com.nacion.android.nacioncostarica.models.ArticleContentItemList;
import com.nacion.android.nacioncostarica.models.Site;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 10/10/2014.
 */
public class ContentPresenterImpl implements ContentPresenter {
    private ContentView contentView;
    private Site site;

    public ContentPresenterImpl(ContentView contentView){
        this.contentView = contentView;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public List<ArticleContentItemList> getArticleContentFromView(int argArticleId){
        return site.getArticleContentsForPhone(argArticleId);
    }

    public boolean articleNotExistsFromView(int argArticleId){
        Article article = site.getArticleById(argArticleId);
        return article.isAEmptyObject();
    }

    @Override
    public void goToLeftSubMenuFromMenuOnTouchListener() {
        contentView.goToLeftSubMenu();
    }

    @Override
    public void backLeftMenuFromMenuOnClickListener() {
        contentView.backLeftMenu();
    }

    @Override
    public void addItemToMainMenuFromSubMenuView(int position){
        contentView.addItemToMainMenu(position);
    }

    @Override
    public void removeItemFromMainMenuSubMenuView(int position){
        contentView.removeItemFromMainMenu(position);
    }

    @Override
    public void removeItemFromMainMenuSubMenuView(String name){
        contentView.removeItemFromMainMenu(name);
    }

    @Override
    public void goToSectionFromAdapter(int boardId) {
        contentView.goToSection(boardId);
    }

    @Override
    public void addMenuToNotificationsFromMenuView(String name) {
        contentView.addMenuToNotification(name);
    }

    @Override
    public void removeMenuFromNotificationsFromMenuView(String name) {
        contentView.removeMenuFromNotification(name);
    }

    @Override
    public Fonts getFonts() {
        return contentView.getFontsFromChildrenViews();
    }

    @Override
    public Context getContextFromContentActivity() {
        return contentView.getContext();
    }
}
