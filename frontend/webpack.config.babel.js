import path from 'path';
import HtmlWebpackPlugin from 'html-webpack-plugin';
import {HotModuleReplacementPlugin} from 'webpack';

export default () => ({
    entry: [
        'react-hot-loader/patch', // Needed to preserve state
        path.join(__dirname, 'src/index.jsx'),
        'webpack-dev-server/client?http://localhost:3000', // webpack dev server host and port
    ],
    output: {
        path: path.join(__dirname, 'dist'),
        filename: 'bundle.js',
    },
    plugins: [
        new HotModuleReplacementPlugin(), // Globally enable hot code replacement
        new HtmlWebpackPlugin({
            filename: 'index.html',
            template: './src/index.html'
        }),
    ],
    module: {
        rules: [
            {
                test: /.jsx?$/,
                exclude: /node_modules/,
                include: path.join(__dirname, 'src'),
                use: [
                    {
                        loader: 'babel-loader',
                        options: {
                            babelrc: false,
                            presets: [
                                ['es2015', {modules: false}],
                                'react',
                            ],
                            plugins: ['react-hot-loader/babel'],
                        }
                    }
                ]
            },
            {
                test: /\.(css|scss|sass)$/,
                loader: 'style-loader!css-loader!sass-loader',
            }
        ],
    },
    devServer: {
        hot: true,
        port: 3000
    }
});