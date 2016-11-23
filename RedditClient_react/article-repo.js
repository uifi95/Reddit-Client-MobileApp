'use strict';
import React, { Component } from 'react';
import {
  StyleSheet,
  TextInput,
  TouchableHighlight,
  AsyncStorage,
  Text,
  View
} from 'react-native';
import Button from 'react-native-button';

class ArticleRepo extends Component {

	constructor(){
		super();
		this.list = {
			articles: [
			{title: 'wow1', content: 'content1'},
			{title: 'wow2', content: 'content2'},
			{title: 'wow3', content: 'content3'}			
			]
		}
    this.itemstyles = function(index){
      return index % 2 == 0  ? styles.listitem : styles.listitem2;
    }
	}

  	navigate(routeName, data) {
      		this.props.navigator.push({
        		name: routeName,
        		data: data
        	});
    	}

	redirect(routeName, accessToken){
		this.props.navigator.push({
			name: routeName
		});
	}

	render() {

		const l = this.list.articles.map((data, index) => {
			return (
				<View key={index}>
					<Button onPress={ this.navigate.bind(this, 'editArticle', data)} style={this.itemstyles(index)} >
					  {data.title + "\n" + data.content}
					</Button>
				</View>
			)
		})


		return (
			<View style={styles.container}>
				<Text style={styles.heading}>Article list:</Text>
				{l}
			</View>
		);
	}
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'flex-start',
    alignItems: 'center',
    backgroundColor: '#424242',
    padding: 10,
    paddingTop: 80
  },
  listitem:{
    padding: 8,
    paddingLeft: 35,
    paddingRight: 35,
    color: "#E0E0E0",
    backgroundColor: "#9E9E9E"
  },
  listitem2:{
    padding: 8,
    paddingLeft: 35,
    paddingRight: 35,
    color: "#E0E0E0",
    backgroundColor: "#757575"
  },
  input: {
    height: 50,
    marginTop: 10,
    padding: 4,
    fontSize: 18,
    borderWidth: 1,
    borderColor: '#48bbec'
  },
  button: {
    height: 50,
    backgroundColor: '#48BBEC',
    alignSelf: 'stretch',
    marginTop: 10,
    justifyContent: 'center'
  },
  buttonText: {
    fontSize: 22,
    color: '#FFF',
    alignSelf: 'center'
  },
  heading: {
    fontSize: 25,
    color: "white",
    marginBottom: 15,
  },
  error: {
    color: 'red',
    paddingTop: 10
  },
  success: {
    color: 'green',
    paddingTop: 10
  },
  loader: {
    marginTop: 20
  }
});

export default ArticleRepo
