/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  TextInput,
  Linking,
  Navigator
} from 'react-native';

import Button from 'react-native-button';

import EditArticle from './edit-article';
import ArticleRepo from './article-repo';
import Root from './root';

export default class RedditClient_react extends Component {

	renderScene(route, navigator) {
		
		if(route.name == 'root') {
			return <Root navigator={navigator} />
		}
		if(route.name == 'articlelist') {
			return <ArticleRepo navigator={navigator} />
		}
		if(route.name == 'editArticle') {
        		return <EditArticle navigator={navigator} article={route.data}/>
     		}
	}

	render() {
		console.log("here");
	      return (
		<View style={styles.container}>
			<Navigator
			initialRoute={{name: 'root' }}
			renderScene={this.renderScene.bind(this)}
			style={{padding: 100}}
			/>
      		</View>
	      );
        }
}

const styles = StyleSheet.create({
container: {
    flex: 1,
    backgroundColor: '#424242',
  },
});

AppRegistry.registerComponent('RedditClient_react', () => RedditClient_react);
