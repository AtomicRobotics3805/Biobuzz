{
  "startPoint": {
    "x": 62.4,
    "y": 8.1,
    "locked": false,
    "headingDeg": 90
  },
  "lines": [
    {
      "id": "line-mv8ps4jn-quhav6",
      "color": "#ffc516",
      "name": "Path 1",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "kind": "atomic",
      "endPoint": {
        "x": 10.95,
        "y": 7.21
      },
      "controlPoints": [
        {
          "x": 52.2,
          "y": 25
        },
        {
          "x": 28.6,
          "y": 7.21
        }
      ],
      "heading": {
        "type": "piecewise",
        "startDeg": 270,
        "endDeg": 270,
        "piecewiseHeading": {
          "segments": [
            {
              "startProgress": 0,
              "endProgress": 0.03514384180828831,
              "interpolationType": "constant",
              "reversed": false,
              "continueFromPrevious": false,
              "parameters": {
                "degrees": 90
              }
            },
            {
              "startProgress": 0.03514384180828831,
              "endProgress": 0.7667731349040826,
              "interpolationType": "tangential",
              "reversed": false,
              "continueFromPrevious": false
            },
            {
              "startProgress": 0.7667731349040826,
              "endProgress": 0.8642173182983891,
              "interpolationType": "linear",
              "reversed": false,
              "continueFromPrevious": true,
              "parameters": {
                "startDeg": 0,
                "endDeg": 180
              }
            },
            {
              "startProgress": 0.8642173182983891,
              "endProgress": 1,
              "interpolationType": "constant",
              "reversed": false,
              "continueFromPrevious": true,
              "parameters": {
                "degrees": 0
              }
            }
          ]
        },
        "degrees": 180
      }
    },
    {
      "id": "line-mveqf95z-tnmhcz",
      "color": "#8B99B9",
      "name": "",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "kind": "atomic",
      "endPoint": {
        "x": 11,
        "y": 96
      },
      "controlPoints": [
        {
          "x": 26.3,
          "y": 48.5
        }
      ],
      "heading": {
        "type": "piecewise",
        "reverse": true,
        "degrees": 0,
        "startDeg": 180,
        "endDeg": 0,
        "piecewiseHeading": {
          "segments": [
            {
              "startProgress": 0,
              "endProgress": 0.16773162326026808,
              "interpolationType": "constant",
              "reversed": false,
              "continueFromPrevious": false,
              "parameters": {
                "degrees": 180
              }
            },
            {
              "startProgress": 0.16773162326026808,
              "endProgress": 0.7619807540608091,
              "interpolationType": "tangential",
              "reversed": false,
              "continueFromPrevious": false
            },
            {
              "startProgress": 0.7619807540608091,
              "endProgress": 1,
              "interpolationType": "linear",
              "reversed": false,
              "continueFromPrevious": true,
              "parameters": {
                "startDeg": 0,
                "endDeg": 0
              }
            }
          ]
        }
      }
    }
  ],
  "shapes": [
    {
      "id": "triangle-1",
      "name": "Opposing Side",
      "vertices": [
        {
          "x": 141.5,
          "y": 0
        },
        {
          "x": 141.5,
          "y": 141.5
        },
        {
          "x": 70.75,
          "y": 141.5
        },
        {
          "x": 70.75,
          "y": 0
        }
      ],
      "color": "#dc2626",
      "fillColor": "#ff6b6b"
    },
    {
      "id": "triangle-2",
      "name": "Hive Leg",
      "vertices": [
        {
          "x": 51,
          "y": 48
        },
        {
          "x": 51,
          "y": 93
        },
        {
          "x": 45,
          "y": 93
        },
        {
          "x": 45,
          "y": 48
        }
      ],
      "color": "#2563eb",
      "fillColor": "#60a5fa"
    }
  ],
  "sequence": [
    {
      "kind": "path",
      "lineId": "line-mv8ps4jn-quhav6"
    },
    {
      "kind": "path",
      "lineId": "line-mveqf95z-tnmhcz"
    }
  ],
  "fieldPoints": [],
  "activePaths": [],
  "settings": {
    "xVelocity": 75,
    "yVelocity": 65,
    "aVelocity": 3.141592653589793,
    "kFriction": 0.1,
    "rWidth": 16.1,
    "rHeight": 14.4,
    "safetyMargin": 1,
    "maxVelocity": 40,
    "maxAcceleration": 30,
    "maxDeceleration": 30,
    "fieldMap": "biobuzz.webp",
    "robotImage": "/robot.png",
    "showGhostPaths": false,
    "showOnionLayers": false,
    "onionLayerSpacing": 3,
    "onionColor": "#dc2626",
    "onionNextPointOnly": false,
    "showHeadingArrow": false,
    "showCurrentTValue": false,
    "leftPanelWidth": 256,
    "rightPanelWidth": 620,
    "headingArrowLength": 50,
    "headingArrowColor": "#ffffff",
    "headingArrowThickness": 2,
    "pathOpacity": 1,
    "leftPanelMinWidth": 0,
    "rightPanelMinWidth": 0,
    "penToolMaxPaths": 8,
    "curveThroughMaxPoints": 4,
    "experimentalFeatures": {
      "optimize": true,
      "curveThrough": false,
      "obstacles": true
    }
  },
  "version": "1.5.0",
  "timestamp": "2026-10-19T04:27:37.102Z"
}