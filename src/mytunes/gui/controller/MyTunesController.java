package mytunes.gui.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.be.SongGenre;
import mytunes.bll.MyTunesBusiness;
import mytunes.gui.model.ListViewSongsModel;
import mytunes.gui.model.TableViewPlaylistsModel;
import mytunes.gui.model.TableViewSongsModel;
import mytunes.gui.view.NewEditDialog;
import mytunes.gui.view.NewPlaylistDialog;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MyTunesController implements Initializable {


    public TableView<Song> tblViewRight;
    private TableViewSongsModel tvSongsModel;
    public TableColumn<Song, String> tblColumnTitle;
    public TableColumn<Song, String> tblColumnArtist;
    public TableColumn<Song, String> tblColumnGenre;
    public TableColumn<Song, String> tblColumnTime;

    public TableView<Playlist> tblViewLeft;
    private TableViewPlaylistsModel tvPlaylistsModel;
    public TableColumn<Playlist, String> tblColumnName;
    public TableColumn<Playlist, String> tblColumnSongs;
    public TableColumn<Playlist, String> tblColumnPlaylistTime;

    public ListView<Song> lstViewMiddle;
    private ListViewSongsModel lvSongsModel;

    public Label lblSongPlaying;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tvSongsModel = new TableViewSongsModel();
        tblViewRight.setItems(tvSongsModel.getSongsList());
        tvPlaylistsModel = new TableViewPlaylistsModel();
        tblViewLeft.setItems(tvPlaylistsModel.getPlaylistList());
        lvSongsModel = new ListViewSongsModel();
        initTables();

    }

    /*
        Adds cell value factories to columns in tables
        a.k.a takes the desired values from objects, so we don't have to do it manually
     */
    private void initTables() {
        //right side
        tblColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblColumnArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        tblColumnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        //tblColumnTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        //left side
        tblColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        //tblColumnSongs.setCellValueFactory(new PropertyValueFactory<>(""));
        //tblColumnPlaylistTime.setCellValueFactory(new PropertyValueFactory<>(""));

        Song mySong = new Song("PepoSad", "Lil Pepo", SongGenre.Rap, "nosauce");
        List<Song> mySongs = new ArrayList<>();
        Playlist myPlaylist = new Playlist("My sad playlist", mySongs);
        tblViewLeft.getItems().add(myPlaylist);
        tblViewRight.getItems().add(mySong);
    }

    /*
        RIGHT SIDE
     */

    /*
        Opens a Dialog for creating a new Song
        adds the Song when APPLY is clicked
     */
    public void handleNewSong(ActionEvent event) {
        System.out.println("New Song");
        NewEditDialog dialog = new NewEditDialog();
        Optional<Song> result = dialog.showAndWait();
        result.ifPresent(response -> {
            tvSongsModel.addSong(response);
            System.out.println(response);
        });
    }

    /*
        Get selected Item (Song) from Right TableView,
        if Song is selected, open a Dialog & fill its fields with the Song properties
        (=> nice UX - they don't have to retype everything everytime)
        edit the song when APPLY is clicked
     */
    public void handleEditSong(ActionEvent event) {
        System.out.println("Edit Song");
        Song selectedSong = tblViewRight.getSelectionModel().getSelectedItem();
        if(selectedSong != null){
            NewEditDialog dialog = new NewEditDialog();
            dialog.setFields(selectedSong);
            Optional<Song> result = dialog.showAndWait();
            result.ifPresent(response -> {
                tvSongsModel.edit(selectedSong, response);
                System.out.println(response);
            });
        }
    }

    //Deletes the selected song on the TableView
    public void handleDeleteSong(ActionEvent event) {
        System.out.println("Delete Song");
        Song selectedSong = tblViewRight.getSelectionModel().getSelectedItem();
        if(selectedSong != null)
        {
            tvSongsModel.deleteSong(selectedSong);
        }
    }

    public void handleSongToPlaylist(ActionEvent event) {
        System.out.println("To Playlist");

    }

    /*
        LEFT SIDE
     */
    public void handleNewPlaylist(ActionEvent event) {
        System.out.println("New Playlist");
        NewPlaylistDialog dialog = new NewPlaylistDialog();
        Optional<Playlist> result = dialog.showAndWait();
        result.ifPresent(response -> {
            tvPlaylistsModel.addPlaylist(response);
            System.out.println(response);
        });
    }


    public void handleEditPlaylist(ActionEvent event) {
        System.out.println("Edit Playlist");
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(selectedPlaylist != null){
            NewPlaylistDialog dialog = new NewPlaylistDialog();
            dialog.setFields(selectedPlaylist);
            Optional<Playlist> result = dialog.showAndWait();
            result.ifPresent(response -> {
                tvPlaylistsModel.edit(selectedPlaylist, response);
                System.out.println(response);
            });
        }

    }

    public void handleDeletePlaylist(ActionEvent event) {
        System.out.println("Delete Playlist");
        Playlist selectedPlaylist = tblViewLeft.getSelectionModel().getSelectedItem();
        if(selectedPlaylist != null)
        {
            tvPlaylistsModel.deletePlaylist(selectedPlaylist);
        }
    }

    /*
        MIDDLE PART
     */

    public void handleMoveUp(ActionEvent event) {
        System.out.println("Move Up");

    }

    public void handleMoveDown(ActionEvent event) {
        System.out.println("Move Down");

    }

    public void handleRemoveFromPlaylist(ActionEvent event) {
        System.out.println("Remove From Playlist");

    }


    /*
        TOP PART
     */
    public void handlePlay(ActionEvent event) {
        System.out.println("Play");

    }

    public void handlePrevious(ActionEvent event) {
        System.out.println("Previous");

    }

    public void handleNext(ActionEvent event) {
        System.out.println("Next");

    }

    public void handleVolume(MouseEvent mouseEvent) {
        System.out.println("Change of Volume");

    }

    public void handleFilter(ActionEvent event) {
        System.out.println("Filter");

    }


}
